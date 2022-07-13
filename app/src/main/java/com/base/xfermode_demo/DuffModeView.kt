package com.base.xfermode_demo

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable


/**
 * @author jiangshiyu
 * @date 2022/7/13
 */
class DuffModeView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) :
    View(context, attrs, defStyleAttr) {
    private var paint: Paint? = null
    private var mWidth = 0
    private var mHeight = 0
    private var srcBm: Bitmap? = null
    private var dstBm: Bitmap? = null


    //混合模式
    private var duffXMode = PorterDuffXfermode(PorterDuff.Mode.ADD)

    @JvmOverloads
    constructor(context: Context?, @Nullable attrs: AttributeSet? = null) : this(context, attrs, 0)

    private fun init() {
        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE, null)
        //初始化画笔
        paint = Paint()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mWidth = right - left
        mHeight = bottom - top
        srcBm = createSrcBitmap(width, height)
        dstBm = createDstBitmap(width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val layerID = canvas.saveLayer(
            0f, 0f, width.toFloat(), height.toFloat(), paint
        )

        canvas.apply {
            drawBitmap(dstBm!!, 0f, 0f, paint)
            paint?.xfermode = duffXMode
            drawBitmap(srcBm!!, 0f, 0f, paint)
            paint?.xfermode = null
            restoreToCount(layerID)
        }
    }

    private fun createDstBitmap(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val dstPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        dstPaint.color = Color.parseColor("#00b7ee")
        canvas.drawCircle(width / 3f, height / 3f, width / 3f, dstPaint)
        return bitmap
    }

    private fun createSrcBitmap(width: Int, height: Int): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        val scrPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        scrPaint.color = Color.parseColor("#ec6941")
        canvas.drawRect(Rect(width / 3, height / 3, width, height), scrPaint)
        return bitmap
    }

    init {
        init()
    }


    /**
     * 设置混合模式
     */
    fun setPorterMode(mode: PorterDuff.Mode) {
        duffXMode = PorterDuffXfermode(mode)
    }
}
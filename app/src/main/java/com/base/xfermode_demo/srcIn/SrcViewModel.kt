package com.base.xfermode_demo.srcIn

import android.R
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.opengl.ETC1.getHeight
import android.opengl.ETC1.getWidth
import androidx.lifecycle.ViewModel
import com.base.xfermode_demo.dstout.DstOutViewModel
import com.blankj.utilcode.util.ImageUtils
import io.reactivex.Observable
import java.io.File


/**
 * @author jiangshiyu
 * @date 2022/7/11
 */
class SrcViewModel : ViewModel() {


    /**
     * 绘制圆形图片
     */
    fun drawCirclePicture(sourceBitmap: Bitmap): Observable<File> {
        return Observable.create { emitter ->

            val bitmapShade = Bitmap.createBitmap(
                sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888
            )
            val paint = Paint()
            paint.isAntiAlias = true

            val canvas = Canvas(bitmapShade)
            canvas.drawCircle(
                sourceBitmap.width / 2f, sourceBitmap.height / 2f, sourceBitmap.width / 2f, paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)

            val resultCompress = File(DstOutViewModel.ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            bitmapShade.recycle()
            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }


    /**
     * 绘制椭圆形图片
     */
    fun drawOvalPicture(sourceBitmap: Bitmap): Observable<File> {
        return Observable.create { emitter ->

            val bitmapShade = Bitmap.createBitmap(
                sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888
            )
            val paint = Paint()
            paint.isAntiAlias = true

            val canvas = Canvas(bitmapShade)
            canvas.drawOval(
                RectF(0f, 0f, sourceBitmap.width / 2f, sourceBitmap.height / 2f), paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)

            val resultCompress = File(DstOutViewModel.ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            bitmapShade.recycle()
            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }


    /**
     * 绘制圆角矩形图片
     */
    fun drawRoundPicture(sourceBitmap: Bitmap): Observable<File> {
        return Observable.create { emitter ->

            val bitmapShade = Bitmap.createBitmap(
                sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888
            )
            val paint = Paint()
            paint.isAntiAlias = true

            val canvas = Canvas(bitmapShade)
            canvas.drawRoundRect(
                RectF(0f, 0f, sourceBitmap.width / 2f, sourceBitmap.height / 2f), 20f, 20f, paint
            )
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            canvas.drawBitmap(sourceBitmap, 0f, 0f, paint)

            val resultCompress = File(DstOutViewModel.ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            bitmapShade.recycle()
            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }


    /**
     * 反色
     */
    fun getRoleColor(sourceBitmap: Bitmap): Observable<File> {

        return Observable.create { emitter ->

            val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
            val bitmapShade = Bitmap.createBitmap(
                sourceBitmap.width, sourceBitmap.height, Bitmap.Config.ARGB_8888
            )

            val canvas = Canvas(bitmapShade)
            val bitmapWidth = sourceBitmap.width
            val bitmapHeight = sourceBitmap.height
            val matrix = Matrix()
            matrix.setScale(3f, 3f)
            canvas.drawBitmap(sourceBitmap, matrix, mPaint)
            mPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            mPaint.color = Color.RED
            canvas.drawRect(
                RectF(0f, 0f, (bitmapWidth * 3).toFloat(), (bitmapHeight * 3).toFloat()), mPaint
            )
            val resultCompress = File(DstOutViewModel.ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            emitter.onNext(resultCompress)
            emitter.onComplete()
        }


    }
}
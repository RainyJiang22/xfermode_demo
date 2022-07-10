package com.base.xfermode_demo.dstout

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import androidx.lifecycle.ViewModel
import com.base.xfermode_demo.XferModeApplication
import com.blankj.utilcode.util.ImageUtils
import io.reactivex.Observable
import java.io.File

/**
 * @author jiangshiyu
 * @date 2022/7/10
 */
class DstOutViewModel : ViewModel() {


    companion object {
        val ERASE_DIR =
            File(XferModeApplication.get().filesDir, "dst_temp")
    }


    /**
     * sourceBitmap:源图像
     * dstBitmap:目标图像
     */
    fun eraseBlack(sourceBitmap: Bitmap, dstBitmap: Bitmap): Observable<File> {
        return Observable.create { emitter ->

            val paint = Paint()
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_OUT)

            val bitmapShade = Bitmap.createBitmap(
                sourceBitmap.width,
                sourceBitmap.height, Bitmap.Config.ARGB_8888
            )

            //一张黑色的图进行混合，如果相交部分完全透明则不过滤，不透明则过滤掉
            Canvas(bitmapShade).apply {
                drawColor(Color.WHITE)
                drawBitmap(sourceBitmap, 0f, 0f, paint)
            }


            val bitmapResult = Bitmap.createBitmap(
                sourceBitmap.width,
                sourceBitmap.height, Bitmap.Config.ARGB_8888
            )
            Canvas(bitmapResult).apply {
                //目标图，黑白图的结果图目标,使用DST_OUT，相交部分透明的不过滤,不透明也就是白色的部分过滤掉
                //就达到了去除黑色背景的效果
                drawBitmap(dstBitmap, 0f, 0f, null)
                drawBitmap(bitmapShade, 0f, 0f, paint)
            }
            val resultCompress = File(ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }


}
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

            val bitmapResult = Bitmap.createBitmap(
                sourceBitmap.width,
                sourceBitmap.height, Bitmap.Config.ARGB_8888
            )
            Canvas(bitmapResult).apply {
                drawColor(Color.WHITE)
                drawBitmap(sourceBitmap, 0f, 0f, paint)
                drawBitmap(dstBitmap, 0f, 0f, paint)
            }

            paint.xfermode = null

            val resultCompress = File(ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapResult, resultCompress, Bitmap.CompressFormat.PNG, true)

            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }


}
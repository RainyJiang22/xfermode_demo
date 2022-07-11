package com.base.xfermode_demo.srcIn

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
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
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)

            Canvas(bitmapShade).apply {
                drawOval(
                    RectF(0f, 0f, sourceBitmap.width.toFloat(), sourceBitmap.height.toFloat()), paint)
                drawBitmap(sourceBitmap, 0f, 0f, paint)
            }

            val resultCompress = File(DstOutViewModel.ERASE_DIR, "origin.png")
            ImageUtils.save(bitmapShade, resultCompress, Bitmap.CompressFormat.PNG, true)

            emitter.onNext(resultCompress)
            emitter.onComplete()
        }
    }
}
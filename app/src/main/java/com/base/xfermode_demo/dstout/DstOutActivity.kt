package com.base.xfermode_demo.dstout

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.base.xfermode_demo.R
import com.base.xfermode_demo.base.BaseActivity
import com.base.xfermode_demo.databinding.ActivityDstOutBinding
import com.blankj.utilcode.util.ImageUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author jiangshiyu
 * @date 2022/7/10
 */
class DstOutActivity : BaseActivity<ActivityDstOutBinding>() {

    private val viewModel by viewModels<DstOutViewModel>()

    @SuppressLint("CheckResult")
    override fun init(savedInstanceState: Bundle?) {


        val sourceBitmap = ImageUtils.getBitmap(R.drawable.source)
        val dstBitmap = ImageUtils.getBitmap(R.drawable.dst)


        binding?.ivResult?.setImageBitmap(dstBitmap)

        binding?.btnErase?.setOnClickListener {
            viewModel.eraseBlack(sourceBitmap, dstBitmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    sourceBitmap.recycle()
                    dstBitmap.recycle()
                    binding?.ivResult?.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                }, {
                    Log.e("Dst", "init: ${it.message}")
                })
        }
    }

}
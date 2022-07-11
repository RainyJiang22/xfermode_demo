package com.base.xfermode_demo.srcIn

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.viewModels
import com.base.xfermode_demo.R
import com.base.xfermode_demo.base.BaseActivity
import com.base.xfermode_demo.databinding.ActivitySrcInBinding
import com.blankj.utilcode.util.ImageUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author jiangshiyu
 * @date 2022/7/11
 */
class SrcInTestActivity : BaseActivity<ActivitySrcInBinding>() {

    private val srcViewModel by viewModels<SrcViewModel>()

    @SuppressLint("CheckResult")
    override fun init(savedInstanceState: Bundle?) {

        val sourceBitmap = ImageUtils.getBitmap(R.drawable.srcintest)

        binding?.ivResult?.setImageBitmap(sourceBitmap)

        binding?.btnCircle?.setOnClickListener {
            //绘制圆形图片
            srcViewModel.drawRoundPicture(sourceBitmap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    binding?.ivResult?.setImageBitmap(BitmapFactory.decodeFile(it.absolutePath))
                }
        }

    }
}
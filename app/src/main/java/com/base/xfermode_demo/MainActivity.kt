package com.base.xfermode_demo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.base.xfermode_demo.base.BaseActivity
import com.base.xfermode_demo.databinding.ActivityRouterBinding
import com.base.xfermode_demo.dstout.DstOutActivity
import com.base.xfermode_demo.srcIn.SrcInTestActivity

class RouterActivity : BaseActivity<ActivityRouterBinding>() {

    override fun init(savedInstanceState: Bundle?) {

        binding?.btnDstOut?.setOnClickListener {
            startActivity(Intent(this, DstOutActivity::class.java))
        }

        binding?.btnSrcIn?.setOnClickListener {
            startActivity(Intent(this, SrcInTestActivity::class.java))
        }


    }
}
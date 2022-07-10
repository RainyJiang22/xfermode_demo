package com.base.xfermode_demo.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

/**
 * @author jiangshiyu
 * @date 2022/7/10
 */
abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {

    var binding: V? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContentView()
        init(savedInstanceState)
    }

    private fun initContentView() {
        val types = (javaClass.genericSuperclass as ParameterizedType).actualTypeArguments
        val aClass = types[0] as Class<V>
        try {
            binding =
                aClass.getDeclaredMethod("inflate", LayoutInflater::class.java)
                    .invoke(null, getLayoutInflater()) as V?
            super.setContentView(binding?.root)
        } catch (e: Error) {
            e.printStackTrace();
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }


    fun binding(block: V?.() -> Unit) {
        block.invoke(binding)
    }

    abstract fun init(savedInstanceState: Bundle?)

}
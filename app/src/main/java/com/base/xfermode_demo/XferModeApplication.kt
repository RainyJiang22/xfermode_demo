package com.base.xfermode_demo

import android.app.Application

/**
 * @author jiangshiyu
 * @date 2022/7/10
 */
class XferModeApplication : Application() {

    companion object {
        lateinit var instance: XferModeApplication

        fun get(): XferModeApplication {
            return instance
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
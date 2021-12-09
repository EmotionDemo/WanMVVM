package com.lfh.wanmvvm

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.lfh.wanmvvm.BuildConfig.DEBUG
import java.util.logging.Level

class MyApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
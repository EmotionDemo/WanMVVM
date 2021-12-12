package com.lfh.wanmvvm.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApp : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private lateinit var context: Context
        public fun getContext() = context

    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
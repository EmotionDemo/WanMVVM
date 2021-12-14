package com.lfh.wanmvvm.logic.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initBinding()
        initViewModel()
        observe()
        initView()

    }

    abstract fun initViewModel()
    abstract fun observe()
    abstract fun initBinding()
    abstract fun initView()
}
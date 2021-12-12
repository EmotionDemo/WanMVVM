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
        initViewModel(this, BaseViewModel::class.java)
        initView()
        observe()
    }

    open fun <T : ViewModel> initViewModel(owner: ViewModelStoreOwner, modelClass: Class<T>): T {
        return ViewModelProvider(owner).get(modelClass)
    }

    //init Observe
    open fun observe() {}
    open fun initBinding() {}
    abstract fun initView()
}
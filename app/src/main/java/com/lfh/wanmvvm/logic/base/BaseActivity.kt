package com.lfh.wanmvvm.logic.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lfh.wanmvvm.util.ColorUtils
import com.lfh.wanmvvm.util.StatusUtils

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setImmersionBar()
        setStatusBarColor()
        initBinding()
        initViewModel()
        observe()
        initView()
    }

    abstract fun initViewModel()
    abstract fun observe()
    abstract fun initBinding()
    abstract fun initView()

    fun setImmersionBar() {
        StatusUtils.setSystemStatus(this, true, true)
    }

    fun setStatusBarColor() {
        StatusUtils.setUseStatusBarColor(this, ColorUtils.parseColor("#ffffff"))
    }
}
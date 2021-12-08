package com.lfh.wanmvvm.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.logic.viewmodel.LoginViewModel

class LoginActivity:AppCompatActivity() {

    val loginModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

}
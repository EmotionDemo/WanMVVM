package com.lfh.wanmvvm.logic.login

import android.content.Intent
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.ActivityLoginBinding
import com.lfh.wanmvvm.logic.base.BaseActivity
import com.lfh.wanmvvm.ui.activity.RegisterActivity
import com.lfh.wanmvvm.util.toast

/**
 * author:love ZhaoXiaoYu
 */

class LoginActivity : BaseActivity() {

    val TAG: String = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    /**
     * 初始化binding
     */
    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    /**
     * 初始化ViewModel
     */
    override fun initViewModel() {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
    }

    /**
     * 初始化View
     */
    override fun initView() {
        binding.btnLogin.setOnClickListener {
            if (loginViewModel.userName.get()!!.isEmpty()) {
                toast("请编辑用户名!")
                return@setOnClickListener
            }
            if (loginViewModel.password.get()!!.isEmpty()) {
                toast("请输入密码")
                return@setOnClickListener
            }
            setViewStatus(false)
            loginViewModel.login()
        }

        binding.ivRegister.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    /**
     * 添加livedata观察
     */
    override fun observe() {
        loginViewModel.loginLiveData.observe(this, {
            setViewStatus(true)
            toast("欢迎回来：" + it.nickname)
        })

        loginViewModel.errorLiveData.observe(this, {
            setViewStatus(true)
            toast(it.errorMsg)
        })

    }

    /**
     * 设置登陆页控件状态
     */
    fun setViewStatus(loginOk: Boolean) {
        binding.btnLogin.isEnabled = loginOk
        binding.etPwd.isEnabled = loginOk
        binding.etUserName.isEnabled = loginOk
        binding.ivRegister.isEnabled = loginOk
        if (!loginOk) {
            binding.loginProBar.visibility = View.VISIBLE
        } else {
            binding.loginProBar.visibility = View.GONE
        }
    }

}
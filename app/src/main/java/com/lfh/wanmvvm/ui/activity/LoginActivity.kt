package com.lfh.wanmvvm.ui.activity

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.ActivityLoginBinding
import com.lfh.wanmvvm.logic.base.BaseActivity
import com.lfh.wanmvvm.logic.login.LoginModel
import com.lfh.wanmvvm.logic.login.LoginViewModel
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
    override fun <T : ViewModel> initViewModel(
        owner: ViewModelStoreOwner,
        modelClass: Class<T>
    ): T {
        loginViewModel = super.initViewModel(this, LoginViewModel::class.java)
        binding.loginViewModel = loginViewModel
        return loginViewModel as T
    }

    /**
     * 初始化ViewModel
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

    }

    override fun observe() {
        loginViewModel.loginLiveData.observe(this, object : Observer<LoginModel> {
            override fun onChanged(it: LoginModel) {
                setViewStatus(true)
                toast("欢迎回来：" + it.nickname)
            }
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
        binding.btnRegister.isEnabled = loginOk
        binding.etPwd.isEnabled = loginOk
        binding.etUserName.isEnabled = loginOk
        if (!loginOk) {
            binding.loginProBar.visibility = View.VISIBLE
        } else {
            binding.loginProBar.visibility = View.GONE
        }
    }

}
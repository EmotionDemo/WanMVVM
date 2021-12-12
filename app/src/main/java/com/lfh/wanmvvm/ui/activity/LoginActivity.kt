package com.lfh.wanmvvm.ui.activity

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.ActivityLoginBinding
import com.lfh.wanmvvm.logic.base.BaseActivity
import com.lfh.wanmvvm.logic.login.LoginViewModel
import com.lfh.wanmvvm.util.toast

class LoginActivity : BaseActivity() {

    val TAG: String = "LoginActivity"
    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    /**
     * 初始化binding
     */
    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
    }

    /**
     * 初始化ViewModel
     */
    override fun <T : ViewModel> initViewModel(owner: ViewModelStoreOwner, modelClass: Class<T>): T {
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
        }
        setViewStatus(false)
        loginViewModel.login()
    }

    override fun observe() {
        loginViewModel.loginLiveData.observe(this, Observer {
            toast("登录成功")
        })
        loginViewModel.errorLiveData.observe(this, Observer {
            setViewStatus(true)
        })
    }

    /**
     * 设置登陆页控件状态
     */
    fun setViewStatus(canUse: Boolean) {
        binding.btnLogin.isEnabled = canUse
        binding.btnRegister.isEnabled = canUse
        binding.etPwd.isEnabled = canUse
        binding.etUserName.isEnabled = canUse
        if (!canUse) {
            binding.loginProBar.visibility = View.VISIBLE
        } else {
            binding.loginProBar.visibility = View.GONE
        }
    }

}
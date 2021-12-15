package com.lfh.wanmvvm.ui.activity

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.lfh.wanmvvm.R
import com.lfh.wanmvvm.databinding.ActivityRegisterBinding
import com.lfh.wanmvvm.logic.base.BaseActivity
import com.lfh.wanmvvm.logic.register.RegisterViewModel
import com.lfh.wanmvvm.util.toast

class RegisterActivity : BaseActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel

    override fun initViewModel() {
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        binding.registerViewModel = viewModel
    }


    override fun observe() {
        viewModel.registerLiveData.observe(this, {
            toast("注册成功!")
            setStatus(true)
        })

        viewModel.errorLiveData.observe(this, {
            toast("注册失败!" + it.errorMsg)
            setStatus(true)
        })
    }

    override fun initBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)
    }

    override fun initView() {
        binding.btnRegister.setOnClickListener {
            val pwd = binding.etPwd.text.toString()
            val rePwd = binding.etRePwd.text.toString()
            if (binding.etUserName.text!!.isEmpty()) {
                toast("请检查用户名！")
                return@setOnClickListener
            }
            if (pwd.isEmpty()) {
                toast("请输入密码！")
                return@setOnClickListener
            }
            if (rePwd.isEmpty()) {
                toast("请再次输入密码！")
                return@setOnClickListener
            }
            if (pwd != rePwd) {
                toast("两次密码不一致！")
                return@setOnClickListener
            }
            setStatus(false)
            viewModel.register()
        }
        binding.ivBack.setOnClickListener {
            finish()
        }
    }

    /**
     * 设置当前界面UI状态
     */
    private fun setStatus(isRegisterFinish: Boolean) {
        binding.etPwd.isEnabled = isRegisterFinish
        binding.etRePwd.isEnabled = isRegisterFinish
        binding.etUserName.isEnabled = isRegisterFinish
        binding.btnRegister.isEnabled = isRegisterFinish
        binding.ivBack.isEnabled = isRegisterFinish
        if (!isRegisterFinish) {
            binding.registerProBar.visibility = View.VISIBLE
        } else {
            binding.registerProBar.visibility = View.GONE
        }
    }


}
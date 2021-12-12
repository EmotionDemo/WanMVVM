package com.lfh.wanmvvm.logic.login

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lfh.wanmvvm.logic.base.BaseViewModel
import kotlinx.coroutines.cancel

class LoginViewModel : BaseViewModel() {

    private val TAG: String = LoginViewModel::class.java.simpleName

    /**
     * 用户名
     */
    val userName = ObservableField<String>().apply {
        set("")
    }

    /**
     * 密码
     */
    val password = ObservableField<String>().apply {
        set("")
    }

    /**
     * 密码是否可见
     */
    val pwdIsVisiable = ObservableField<Boolean>().apply {
        set(false)
    }

    /**
     * 登录LiveData
     */
    val loginLiveData = MutableLiveData<LoginModel>()

    private val loginRepository by lazy {
        //初始化Repository
        LoginRepository()
    }

    /**
     * 登录
     */
    fun login() {
        launch {
            loginLiveData.value = loginRepository.login(userName.get()!!, password.get()!!)
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
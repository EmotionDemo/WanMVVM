package com.lfh.wanmvvm.logic.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfh.wanmvvm.logic.model.LoginModel
import com.lfh.wanmvvm.logic.model.RegisterModel
import com.lfh.wanmvvm.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private var registerLiveData: MutableLiveData<RegisterModel>? = null
    fun getRegisterLiveData() = registerLiveData
    private val TAG: String = LoginViewModel::class.java.simpleName

    /**
     * 登录
     */
    fun login(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.login(userName, password)
        }
    }

    fun register(userName: String, password: String, repassword: String) {
        viewModelScope.launch {
            Log.d(TAG, "44444")
            registerLiveData = Repository.register(
                userName,
                password,
                repassword
            ) as MutableLiveData<RegisterModel>
        }
    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}
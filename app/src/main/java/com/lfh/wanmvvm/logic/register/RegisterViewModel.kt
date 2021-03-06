package com.lfh.wanmvvm.logic.register

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.lfh.wanmvvm.exception.ApiException
import com.lfh.wanmvvm.logic.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class RegisterViewModel : BaseViewModel() {

    private val TAG: String = RegisterViewModel::class.java.simpleName

    val userName = ObservableField<String>().apply {
        set("")
    }

    val password = ObservableField<String>().apply {
        set("")
    }

    val repassword = ObservableField<String>().apply {
        set("")
    }

    val registerLiveData = MutableLiveData<Any>()

    private val registerRepository: RegisterRepository by lazy {
        RegisterRepository()
    }


    fun register() {
        launch {
            try {
                registerLiveData.value = registerRepository.register(userName.get()!!, password.get()!!, repassword.get()!!)
            } catch (e: ApiException) {
                errorLiveData.value = ApiException(e.errorMsg, e.errorCode)
            }
        }
    }
}
package com.lfh.wanmvvm.logic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfh.wanmvvm.logic.model.LoginModel
import com.lfh.wanmvvm.network.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {


    /*  suspend fun register(userName:String ,password:String,repassword:String){
          viewModelScope.launch(Dispatchers.IO){
              val register = Repository.register(userName, password, repassword)
          }

      }*/

    /**
     * 登录
     */
    fun login(userName: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            Repository.login(userName, password)
        }

    }


    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


}
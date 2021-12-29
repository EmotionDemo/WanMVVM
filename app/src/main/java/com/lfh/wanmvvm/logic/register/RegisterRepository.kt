package com.lfh.wanmvvm.logic.register

import android.util.Log
import com.lfh.wanmvvm.logic.base.BaseRepository
import com.lfh.wanmvvm.logic.event.LoginEvent
import com.lfh.wanmvvm.network.RetrofitManager
import com.lfh.wanmvvm.network.WanService
import org.greenrobot.eventbus.EventBus

class RegisterRepository : BaseRepository() {

    val TAG: String = RegisterRepository::class.java.simpleName
    suspend fun register(username: String, password: String, repassword: String) = withIO {
        RetrofitManager.getApiService(WanService::class.java)
            .register(username, password, repassword)
            .data()
            .apply {
                Log.d(TAG, this.toString())
                //发送注册事件
                EventBus.getDefault().post(LoginEvent())
            }
    }


}
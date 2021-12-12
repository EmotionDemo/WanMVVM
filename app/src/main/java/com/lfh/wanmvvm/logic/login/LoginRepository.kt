package com.lfh.wanmvvm.logic.login

import android.widget.Toast
import com.lfh.wanmvvm.app.MyApp
import com.lfh.wanmvvm.constants.Constant.USER_INFO
import com.lfh.wanmvvm.constants.Constant.USER_LOGIN
import com.lfh.wanmvvm.logic.base.BaseRepository
import com.lfh.wanmvvm.logic.event.LoginEvent
import com.lfh.wanmvvm.network.ApiException
import com.lfh.wanmvvm.network.RetrofitManager
import com.lfh.wanmvvm.network.WanService
import com.lfh.wanmvvm.util.SpUtil
import org.greenrobot.eventbus.EventBus

class LoginRepository : BaseRepository() {
    suspend fun login(userName: String, password: String) = withIO {
        RetrofitManager.getApiService(WanService::class.java)
            .login(userName, password)
            .data()
            .apply {
                //保存已经登录的信息
                SpUtil.setObject(USER_INFO, this)
                //保存已登陆标记位
                SpUtil.setBoolean(USER_LOGIN, true)
                //发送登录事件
                EventBus.getDefault().post(LoginEvent())
            }
    }
}
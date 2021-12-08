package com.lfh.wanmvvm.network

import android.accounts.NetworkErrorException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object MainNetWork {
    //创建主要的请求Service
    private val mainService = RetrofitBuilder.create(WanService::class.java)

    //请求主页banner信息
    suspend fun searchBanners() = mainService.searchBanners().await()

    //请求登录信息
    suspend fun login(userName: String, password: String) =
        mainService.login(userName, password).await()

    //注册用户信息
    suspend fun register(userName: String, password: String, repassword: String) =
        mainService.register(userName, password, repassword).await()


    /**
     * 发起网络请求
     */
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    } else {
                        continuation.resumeWithException(NetworkErrorException("网络异常，未请求到"))
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }


}
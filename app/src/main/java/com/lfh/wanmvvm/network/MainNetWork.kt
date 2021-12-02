package com.lfh.wanmvvm.network

import retrofit2.await

object MainNetWork {
    //创建主要的请求Service
    private val mainService = RetrofitBuilder.create(WanService::class.java)

    suspend fun searchBanners() = mainService.searchBanners().await()



}
package com.lfh.wanmvvm.network

import com.lfh.wanmvvm.logic.model.BannerData
import com.lfh.wanmvvm.logic.model.BaseResponse
import retrofit2.Call
import retrofit2.http.GET

interface WanService {
    @GET("/banner/json")
    fun searchBanners(): Call<BaseResponse<List<BannerData>>>



}
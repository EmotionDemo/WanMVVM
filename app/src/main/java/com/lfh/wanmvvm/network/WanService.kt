package com.lfh.wanmvvm.network

import com.lfh.wanmvvm.logic.base.DataResponse
import com.lfh.wanmvvm.logic.model.BannerData
import com.lfh.wanmvvm.logic.model.BaseResponse
import com.lfh.wanmvvm.logic.login.LoginModel
import com.lfh.wanmvvm.logic.model.RegisterModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WanService {

    //获取首页banner
    @GET("/banner/json")
    fun searchBanners(): Call<BaseResponse<List<BannerData>>>

    //注册新用户
    @FormUrlEncoded
    @POST("/user/register")
    fun register(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): Call<RegisterModel>

    //登录
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") password: String
    ): DataResponse<LoginModel>


}
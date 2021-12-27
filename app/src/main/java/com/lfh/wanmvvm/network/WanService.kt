package com.lfh.wanmvvm.network

import com.lfh.wanmvvm.logic.base.DataResponse
import com.lfh.wanmvvm.logic.home.BannerModel
import com.lfh.wanmvvm.logic.login.LoginModel
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface WanService {

    //获取首页banner
    @GET("/banner/json")
    fun searchBanners(): DataResponse<MutableList<BannerModel>>

    //注册新用户
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String
    ): DataResponse<Any>


    //登录
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") password: String
    ): DataResponse<LoginModel>


    //置顶文章
    @GET("/article/top/json")
    suspend fun getTopArticle(): DataResponse<MutableList<BannerModel>>


}
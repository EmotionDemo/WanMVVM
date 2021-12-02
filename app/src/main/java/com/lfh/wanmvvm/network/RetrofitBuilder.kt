package com.lfh.wanmvvm.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://www.wanandroid.com"

    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    fun <T> create(service:Class<T>):T =retrofit.create(service)

    inline fun <reified T> create():T = create(T::class.java)


}
package com.lfh.wanmvvm.network

import android.content.SharedPreferences
import com.franmontiel.persistentcookiejar.ClearableCookieJar
import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.lfh.wanmvvm.app.MyApp
import com.lfh.wanmvvm.constants.Constant.BASE_URL
import com.lfh.wanmvvm.constants.Constant.DEFAULT_TIMEOUT
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object RetrofitFactory {
    private val okHttpClientBuilder: OkHttpClient.Builder
        get() {
            return OkHttpClient.Builder()
                .readTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .connectTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .writeTimeout(DEFAULT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
                .addInterceptor(getLogInterceptor())
                .cookieJar(getCookie())
                .cache(getCache())
        }

    fun createRetrofit():Retrofit{
        val okHttpClient = okHttpClientBuilder.build()
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    /**
     * 日志拦截器
     */
    private fun getLogInterceptor(): Interceptor {
        return HttpLoggingInterceptor("OkHttp").apply {
            setPrintLevel(HttpLoggingInterceptor.Level.BODY)
            setColorLevel(Level.INFO)
        }
    }

    /**
     *获取Cookie持久化
     */
    private fun getCookie(): ClearableCookieJar {
        return PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(MyApp.getContext()))
    }

    /**
     * 获取缓存方式
     */
    private fun getCache(): Cache {
        //缓存100MB
        return Cache(File(MyApp.getContext().cacheDir, "cache"), 1024 * 1024 * 100)
    }

}
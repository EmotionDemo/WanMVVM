package com.lfh.wanmvvm.logic.home

import android.util.Log
import com.lfh.wanmvvm.logic.base.BaseRepository
import com.lfh.wanmvvm.network.RetrofitManager
import com.lfh.wanmvvm.network.WanService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository : BaseRepository() {
    val service = RetrofitManager.getApiService(WanService::class.java)
    val TAG = "HomeRepository"

    //获取banner
    suspend fun getBanners() = withIO {
        service.searchBanners().data()
    }

    //获取首页文章列表
    suspend fun getArticle() = withIO {
        /*val datasss = service.getHomeArticle(0)
        Log.d(TAG,datasss.toString())

        val data = datasss.data()
        Log.d(TAG,data.toString())

        data?.apply {
            Log.d(TAG,"1"+this.data.datas.toString())
            conToLocalData(this.data.datas)
            Log.d(TAG,"2"+this.data.datas.toString())
        }*/
        val topArticle = service.getHomeArticle(0)
        topArticle.clone().enqueue(object : Callback<HomeArticleModel> {
            override fun onResponse(call: Call<HomeArticleModel>, response: Response<HomeArticleModel>) {
                Log.d(TAG, "1" + response.body().toString())
            }

            override fun onFailure(call: Call<HomeArticleModel>, t: Throwable) {
                Log.d(TAG, "2" + t)
                topArticle.clone().cancel()
            }

        })


    }

}
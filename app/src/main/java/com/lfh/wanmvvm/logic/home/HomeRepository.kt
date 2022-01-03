package com.lfh.wanmvvm.logic.home

import com.lfh.wanmvvm.logic.base.BaseRepository
import com.lfh.wanmvvm.logic.base.article.ArticleListModel.Companion.conToLocalData
import com.lfh.wanmvvm.network.RetrofitManager
import com.lfh.wanmvvm.network.WanService

class HomeRepository : BaseRepository() {
    val service = RetrofitManager.getApiService(WanService::class.java)

    //获取banner
    suspend fun getBanners() = withIO {
        service.searchBanners().data()
    }

    //获取首页文章列表
    suspend fun getArticle() = withIO {
        service.getHomeArticle(0).data()?.apply {
            conToLocalData(this.data.datas)
        }
    }

}
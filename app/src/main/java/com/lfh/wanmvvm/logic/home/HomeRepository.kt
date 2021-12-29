package com.lfh.wanmvvm.logic.home

import com.lfh.wanmvvm.logic.base.BaseRepository
import com.lfh.wanmvvm.network.RetrofitManager
import com.lfh.wanmvvm.network.WanService

class HomeRepository : BaseRepository() {

    suspend fun getBanners() = withIO {
        RetrofitManager.getApiService(WanService::class.java)
            .searchBanners().data()
    }

}
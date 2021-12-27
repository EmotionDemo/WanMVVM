package com.lfh.wanmvvm.logic.home

import androidx.lifecycle.MutableLiveData
import com.lfh.wanmvvm.logic.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val repository: HomeRepository by lazy { HomeRepository() }

    //Banner
    private val _banner = MutableLiveData<MutableList<BannerModel>>()

    val banner = _banner

    fun getBanner() {
        launch {
            _banner.value = repository.getBanners()
        }
    }


}
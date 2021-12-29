package com.lfh.wanmvvm.logic.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lfh.wanmvvm.exception.ApiException
import com.lfh.wanmvvm.logic.base.BaseViewModel

class HomeViewModel : BaseViewModel() {

    private val repository: HomeRepository by lazy { HomeRepository() }

    //Banner
    private val _banner = MutableLiveData<MutableList<BannerModel>>()

    //对外暴露的banner
    val banner:LiveData<MutableList<BannerModel>> = _banner

    fun getBanner() {
        launch {
            try {
                _banner.value = repository.getBanners()
            }catch (e:ApiException){
                errorLiveData.value = ApiException(e.errorMsg, e.errorCode)
            }

        }
    }


}
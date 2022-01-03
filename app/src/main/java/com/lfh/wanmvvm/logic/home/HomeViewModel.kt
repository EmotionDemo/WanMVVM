package com.lfh.wanmvvm.logic.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.lfh.wanmvvm.exception.ApiException
import com.lfh.wanmvvm.logic.base.BaseViewModel
import com.lfh.wanmvvm.logic.base.article.ArticleListModel

class HomeViewModel : BaseViewModel() {
    private val TAG:String = "HomeViewModel"
    private val repository: HomeRepository by lazy { HomeRepository() }

    //Banner
    private val _banner = MutableLiveData<MutableList<BannerModel>>()

    //article
    private val _article = MutableLiveData<ArticleModel>()

    //对外暴露的banner
    val banner: LiveData<MutableList<BannerModel>> = _banner

    //对外暴露的article
    val article: LiveData<ArticleModel> = _article

    fun getBanner() {
        launch {
            try {
                _banner.value = repository.getBanners()
            } catch (e: ApiException) {
                errorLiveData.value = ApiException(e.errorMsg, e.errorCode)
            }
        }
    }

    fun getArticle() {
        launch {
            try {
                _article.value = repository.getArticle()
                Log.d(TAG, _article.value?.data.toString())
            } catch (e: ApiException) {
                errorLiveData.value = ApiException(e.errorMsg, e.errorCode)
            }
        }
    }


}
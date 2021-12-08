package com.lfh.wanmvvm.logic

import android.accounts.NetworkErrorException
import androidx.lifecycle.liveData
import com.lfh.wanmvvm.logic.model.BannerData
import com.lfh.wanmvvm.network.MainNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

object Repository {
    fun searchBanners() = liveData<BannerData>(Dispatchers.IO) {
        val result = try {
            val bannerData = MainNetWork.searchBanners()
            if (bannerData.errorCode == 0) {
                Result.success(bannerData)
            } else {
                Result.failure(NetworkErrorException("network fail errorCode is ${bannerData.errorCode}"))
            }
        } catch (e: Exception) {
            Result.failure<BannerData>(e)
        }
        emit(result as BannerData)
    }
}

package com.lfh.wanmvvm.network

import android.accounts.NetworkErrorException
import androidx.lifecycle.liveData
import com.lfh.wanmvvm.logic.model.BannerData
import com.lfh.wanmvvm.logic.model.LoginModel
import com.lfh.wanmvvm.network.MainNetWork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

/**
 * LOVEZhaoXiaoYu
 * forever
 */

object Repository {

    /**
     * 查询banner信息
     */
    fun searchBanners() = liveData(Dispatchers.IO) {
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
        emit(result)
    }


    /**
     * 登录
     */
    fun login(userName: String, password: String) = liveData(Dispatchers.IO) {
        val result = try {
            val loginData = MainNetWork.login(userName, password)
            if (loginData.errorCode == 0) {
                Result.success(loginData)
            } else {
                Result.failure(NetworkErrorException("network fail errorCode is ${loginData.errorCode}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
        emit(result)
    }


    /**
     * 注册
     */
    fun register(userName: String, password: String, repassword: String) =
        liveData(Dispatchers.IO) {
            val result = try {
                val registerData = MainNetWork.register(userName, password, repassword)
                if (registerData.errorCode == 0) {
                    Result.success(registerData)
                } else {
                    Result.failure(NetworkErrorException("network fail errorCode is ${registerData.errorCode}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
            emit(result)
        }


}

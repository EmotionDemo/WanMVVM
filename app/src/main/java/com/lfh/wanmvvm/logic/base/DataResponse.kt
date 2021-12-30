package com.lfh.wanmvvm.logic.base

import com.lfh.wanmvvm.exception.ApiException
import java.io.Serializable

class DataResponse<T>(var errorMsg: String, var errorCode: Int) : Serializable {
    private var data: T? = null
    fun data(): T {
        if (errorCode != 0) {
            throw ApiException(errorMsg, errorCode)
        }
        when (errorCode) {
            0 -> {
                return data!!
            }
            200 -> {
                return data!!
            }
            -1001 -> {
                //未登录
                throw ApiException(errorMsg, errorCode)
            }
            -1 -> {
                throw ApiException(errorMsg, errorCode)
            }
            else -> {
                throw ApiException("network error", -99)
            }
        }
    }



    /**
     * 如果返回的data是空，就new一个对象出来
     */
    fun data(clazz: Class<T>): T {
        when (errorCode) {
            0, 200 -> {
                if (data == null) {
                    data = clazz.newInstance()
                }
                return data!!
            }
            -1001 -> {
                //未登录
                throw ApiException(errorMsg, errorCode)
            }
            -1 -> {
                throw ApiException(errorMsg, errorCode)
            }
        }
        throw ApiException(errorMsg, errorCode)
    }
}
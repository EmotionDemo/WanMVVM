package com.lfh.wanmvvm.logic.base

import com.lfh.wanmvvm.exception.ApiException
import java.io.Serializable

class DataResponse<T> : Serializable {
    private var data: T? = null

    private var errorMsg = ""
    private var errorCode = 0

    fun data(): T {
        when (errorCode) {
            0, 200 -> {
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
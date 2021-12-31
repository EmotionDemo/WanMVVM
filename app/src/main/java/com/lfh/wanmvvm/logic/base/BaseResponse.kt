package com.lfh.wanmvvm.logic.base

/**
* @author:lifenghua
* createTime:2021/12/30 22:23
*/
data class BaseResponse<T>(
    val data: T,
    val errorMsg: String,
    val errorCode: Int,
) {
    fun isSuccessFul(): Boolean {
        return errorCode == 0
    }
}

package com.lfh.wanmvvm.logic.model

data class BaseResponse<T>(val errorCode:Int, val errorMsg:String, val data:T)

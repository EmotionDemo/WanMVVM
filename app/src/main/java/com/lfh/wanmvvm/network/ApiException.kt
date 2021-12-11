package com.lfh.wanmvvm.network

class ApiException (var errorMsg:String,val errorCode:Int):Throwable()
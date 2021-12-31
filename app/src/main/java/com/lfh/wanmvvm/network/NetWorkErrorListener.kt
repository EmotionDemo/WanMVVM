package com.lfh.wanmvvm.network
/**
* desc: 网络异常监听器
* @author :lifenghua
* createData :2021/12/30 14:51
*/
interface NetWorkErrorListener {
     fun onNetError(e:Exception)
}
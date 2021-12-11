package com.lfh.wanmvvm.logic.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfh.wanmvvm.BuildConfig
import com.lfh.wanmvvm.network.ApiException
import com.lfh.wanmvvm.util.toast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.CancellationException


typealias errorViewModel = (e: ApiException) -> Unit

open class BaseViewModel : ViewModel() {
    //错误信息
    val errorLiveData = MutableLiveData<ApiException>()

    //没有更多数据
    val footLiveData = MutableLiveData<Any>()

    //没有数据
    val emptyLiveData = MutableLiveData<Any>()

    /**
     * 处理错误信息
     */
    fun handlerErrorMsg(e: Throwable) {
        val errorMsg = getApiException(e)
        toast(errorMsg.errorMsg)
        errorLiveData.postValue(errorMsg)
    }

    protected fun <T> launch(block: suspend () -> T) {
        viewModelScope.launch {
            runCatching {
                block()
            }.onFailure {
                if (BuildConfig.DEBUG) {
                    it.printStackTrace()
                    return@onFailure
                }
                getApiException(it).apply {
                    withContext(Dispatchers.Main) {
                        toast(errorMsg)
                        //统一响应错误信息
                        errorLiveData.value = this@apply
                    }
                }
            }
        }
    }


    /**
     * launch： 创建协程
       async ： 创建带返回值的协程，返回的是 Deferred 类
       withContext：不创建新的协程，指定协程上运行代码块
       runBlocking：不是 GlobalScope 的 API，可以独立使用，区别是 runBlocking 里面的 delay 会阻塞线程，而 launch 创建的不会
       withContextt这个函数主要可以切换到指定的线程，并在闭包内的逻辑执行结束之后，自动把线程切回去继续执行：
     */
    protected fun <T> launch(block: () -> T, error:errorViewModel?=null) {
        viewModelScope.launch {
            runCatching{
                block()
            }.onFailure {
                it.printStackTrace()
                getApiException(it).apply {
                    withContext(Dispatchers.Main){
                        error?.invoke(this@apply)
                        toast(errorMsg)
                    }
                }
            }
        }
    }

    /**
     * 捕获异常处理
     */
    private fun getApiException(e: Throwable) = when (e) {
        is UnknownHostException -> {
            ApiException("UnknownHostException", -100)
        }
        is JSONException -> {
            ApiException("JSONException", -100)
        }
        is SocketTimeoutException -> {
            ApiException("SocketTimeoutException", -100)
        }
        is ConnectException -> {
            ApiException("ConnectException", -100)
        }
        is HttpException -> {
            ApiException("HttpException", -100)
        }
        is ApiException -> {
            e
        }
        /**
         * 如果协程还在运行，个别机型退出当前界面时，viewModel会通过抛出CancellationException，
         * 强行结束协程，与java中InterruptException类似，所以不必理会,只需将toast隐藏即可
         */
        is CancellationException -> {
            ApiException("", -10)
        }
        else -> {
            ApiException("未知错误", -100)
        }
    }
}
package com.lfh.wanmvvm.logic.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 基础数据类
 */
open class BaseRepository {

    /**
     * 在协程作用域切换至IO线程(任务线程)
     */
    protected suspend fun <T> withIO( t: suspend () -> T): T {
        return withContext(Dispatchers.IO) {
            t.invoke()
        }
    }
}
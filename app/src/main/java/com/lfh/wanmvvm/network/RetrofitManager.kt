package com.lfh.wanmvvm.network

class RetrofitManager {
    companion object {
        /**
         * 用于存储apiService
         */
        private val map = mutableMapOf<Class<*>, Any>()

        /**
         * 初始化一次Retrofit
         */
        private val retrofit = RetrofitFactory.createRetrofit()

        /**
         * 获取Service
         */
        fun <T : Any> getApiService(apiClass: Class<T>): T {
            return getService(apiClass)
        }


        /**
         * 获取ApiService
         */
        private fun <T : Any> getService(apiClass: Class<T>): T {
            return if (map[apiClass] == null) {
                synchronized(RetrofitManager::class.java) {
                    val service = retrofit.create(apiClass)
                    if (map[apiClass] == null) {
                        map[apiClass] = service
                    }
                    service
                }
            } else {
                map[apiClass] as T
            }
        }

    }
}
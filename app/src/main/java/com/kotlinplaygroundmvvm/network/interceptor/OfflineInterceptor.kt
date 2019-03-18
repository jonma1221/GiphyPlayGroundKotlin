package com.kotlinplaygroundmvvm.network.interceptor

import com.kotlinplaygroundmvvm.ui.util.App
import com.kotlinplaygroundmvvm.ui.util.hasNetwork
import okhttp3.Interceptor
import okhttp3.Response

class OfflineInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if(hasNetwork(App.applicationContext())){
            val maxStale = 60 * 60 * 24 * 30 // Offline cache available for 30 days
            request = request.newBuilder()
                .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
                .build()
        }
        return chain.proceed(request)
    }

}

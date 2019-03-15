package com.kotlinplayground.network.interceptor

import com.kotlinplayground.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        // instead of adding the api key as a param every time
        // we add it to a interceptor
        val newUrl = original.url().newBuilder()
            .addQueryParameter("api_key", BuildConfig.GiphyApiKey).build()

        val builder = original.newBuilder().url(newUrl)
        val request = builder.build()
        return chain.proceed(request)
    }

}

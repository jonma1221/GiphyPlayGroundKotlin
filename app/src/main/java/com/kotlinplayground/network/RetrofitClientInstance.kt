package com.kotlinplayground.network

import com.kotlinplayground.network.interceptor.ApiKeyInterceptor
import com.kotlinplayground.network.interceptor.OfflineInterceptor
import com.kotlinplayground.network.interceptor.OnlineInterceptor
import com.kotlinplayground.ui.util.App
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClientInstance {
    private val cacheSize = 10 * 1024 * 1024 // 10 MB

    var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createClient())
            .build()
    }

//    fun getInstance(): Retrofit {
//        if (retrofit == null) {
//            retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(createClient())
//                .build()
//        }
//        return retrofit
//    }

    private fun createClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            cache(Cache(App.applicationContext().cacheDir, cacheSize.toLong()))
            addInterceptor(createHttpLoggingInterceptor())
            addInterceptor(ApiKeyInterceptor())
//            addInterceptor(OfflineInterceptor())
//            addNetworkInterceptor(OnlineInterceptor())
        }
        return builder.build()
    }

    private fun createHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
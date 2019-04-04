package com.kotlinplaygroundmvvm.di

import android.content.Context
import com.google.gson.Gson
import com.kotlinplaygroundmvvm.network.BASE_URL
import com.kotlinplaygroundmvvm.network.RetrofitClientInstance
import com.kotlinplaygroundmvvm.network.interceptor.ApiKeyInterceptor
import com.kotlinplaygroundmvvm.network.interceptor.OfflineInterceptor
import com.kotlinplaygroundmvvm.network.interceptor.OnlineInterceptor
import com.kotlinplaygroundmvvm.ui.util.App
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSourceImpl
import com.kotlinplaygroundmvvm.network.service.GiphyGetService


@Module
class NetworkModule {
    private val cacheSize = 10 * 1024 * 1024 // 10 MB

    @Singleton
    @Provides
    fun providesRetrofit(gson: Gson, client: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    @Singleton
    @Provides
    fun providesGson(): Gson{
        val gsonBuilder = GsonBuilder()
        return gsonBuilder.create()
    }

    @Singleton
    @Provides
    fun providesCache(app: Context): Cache{
        return Cache(app.cacheDir, cacheSize.toLong())
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(cache: Cache): OkHttpClient{
        val builder = OkHttpClient.Builder()
        builder.apply {
            connectTimeout(10, TimeUnit.SECONDS)
            writeTimeout(10, TimeUnit.SECONDS)
            readTimeout(10, TimeUnit.SECONDS)
            cache(cache)
            addInterceptor(createHttpLoggingInterceptor())
            addInterceptor(ApiKeyInterceptor())
//            addInterceptor(OfflineInterceptor())
//            addNetworkInterceptor(OnlineInterceptor())
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun providesGiphyGetService(retrofit: Retrofit): GiphyGetService{
        return retrofit.create(GiphyGetService::class.java)
    }

    @Singleton
    @Provides
    fun providesGiphyDataSource(giphyDataSourceImpl: GiphyDataSourceImpl): GiphyDataSource{
        return giphyDataSourceImpl
    }

    private fun createHttpLoggingInterceptor(): Interceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}
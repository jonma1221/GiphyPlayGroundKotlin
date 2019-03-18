package com.kotlinplaygroundmvvm.network.service

import com.kotlinplaygroundmvvm.data.model.GiphyByIdResponse
import com.kotlinplaygroundmvvm.data.model.GiphyListResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GiphyGetService {
    // rxjava version
    @GET("/v1/gifs/trending")
    fun getTrendingRx(
        @Query("limit") size: Int?,
        @Query("offset") offset: Int?
    ): Single<Response<GiphyListResponse>>

    @GET("/v1/gifs/{gif_id}")
    fun getGiphyByIdRx(@Path("gif_id") gifId: String): Single<Response<GiphyByIdResponse>>


    @GET("/v1/gifs/search")
    fun searchGiphyRx(
        @Query("q") searchQuery: String,
        @Query("offset") offset: Int?
    ): Single<Response<GiphyListResponse>>
}

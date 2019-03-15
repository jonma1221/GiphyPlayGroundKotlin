package com.kotlinplayground.data.source

import com.kotlinplayground.data.model.GiphyData
import io.reactivex.Single

interface GiphyDataSource {
    fun getGiphy(id: String): Single<GiphyData>
    fun getTrending(offset: Int): Single<List<GiphyData>>
    fun searchGiphy(query: String, offset: Int): Single<List<GiphyData>>
}
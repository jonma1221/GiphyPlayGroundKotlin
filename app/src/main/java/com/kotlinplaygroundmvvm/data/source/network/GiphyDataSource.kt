package com.kotlinplaygroundmvvm.data.source.network

import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import io.reactivex.Single

interface GiphyDataSource {
    fun getGiphy(id: String): Single<GiphyObject>
    fun getTrending(offset: Int): Single<List<GiphyObject>>
    fun searchGiphy(query: String, offset: Int): Single<List<GiphyObject>>
}
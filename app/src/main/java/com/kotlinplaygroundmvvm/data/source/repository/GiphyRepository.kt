package com.kotlinplaygroundmvvm.data.source.repository

import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface GiphyRepository {
    fun getGiphy(id: String): Single<GiphyObject>
    fun getTrending(offset: Int): Observable<List<GiphyObject>>
    fun searchGiphy(query: String, offset: Int): Single<List<GiphyObject>>
}
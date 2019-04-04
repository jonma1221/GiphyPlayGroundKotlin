package com.kotlinplaygroundmvvm.data.source.network

import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.network.RetrofitClientInstance
import com.kotlinplaygroundmvvm.network.service.GiphyGetService
import io.reactivex.Single
import javax.inject.Inject

class GiphyDataSourceImpl @Inject constructor(var giphyGetService: GiphyGetService): GiphyDataSource {
//    private var giphyGetService: GiphyGetService
//            = RetrofitClientInstance.retrofit.create(GiphyGetService::class.java)


    override fun getGiphy(id: String): Single<GiphyObject> {
        return giphyGetService.getGiphyByIdRx(id)
            .flatMap { Single.just(it.body()?.giphyObject) }
    }

    override fun getTrending(offset: Int): Single<List<GiphyObject>> {
        return giphyGetService.getTrendingRx(15, offset)
            .flatMap { Single.just(it.body()?.list) }
    }

    override fun searchGiphy(query: String, offset: Int): Single<List<GiphyObject>> {
        return giphyGetService.searchGiphyRx(query, offset)
            .flatMap { Single.just(it.body()?.list) }
    }
}
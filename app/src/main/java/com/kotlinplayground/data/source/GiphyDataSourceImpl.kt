package com.kotlinplayground.data.source

import com.kotlinplayground.data.model.GiphyData
import com.kotlinplayground.network.RetrofitClientInstance
import com.kotlinplayground.network.service.GiphyGetService
import io.reactivex.Single

class GiphyDataSourceImpl: GiphyDataSource {
    private var giphyGetService: GiphyGetService
            = RetrofitClientInstance.retrofit.create(GiphyGetService::class.java)

    override fun getGiphy(id: String): Single<GiphyData> {
        return giphyGetService.getGiphyByIdRx(id)
            .flatMap { Single.just(it.body()?.giphyData) }
    }

    override fun getTrending(offset: Int): Single<List<GiphyData>> {
        return giphyGetService.getTrendingRx(15, offset)
            .flatMap { Single.just(it.body()?.list) }
    }

    override fun searchGiphy(query: String, offset: Int): Single<List<GiphyData>> {
        return giphyGetService.searchGiphyRx(query, offset)
            .flatMap { Single.just(it.body()?.list) }
    }
}
package com.kotlinplaygroundmvvm.data.source

import com.kotlinplaygroundmvvm.data.model.GiphyData
import io.reactivex.Single

class GiphyRepository : GiphyDataSource {
    //todo - add local source and better way of injecting data sources
    override fun getGiphy(id: String): Single<GiphyData> {
        return GiphyDataSourceImpl().getGiphy(id)
    }

    override fun getTrending(offset: Int): Single<List<GiphyData>> {
        return GiphyDataSourceImpl().getTrending(offset)
    }

    override fun searchGiphy(query: String, offset: Int): Single<List<GiphyData>> {
        return GiphyDataSourceImpl().searchGiphy(query, offset)
    }
}
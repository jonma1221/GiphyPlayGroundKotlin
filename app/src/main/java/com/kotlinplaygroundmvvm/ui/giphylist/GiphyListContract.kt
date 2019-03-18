package com.kotlinplaygroundmvvm.ui.giphylist

import com.kotlinplaygroundmvvm.data.model.GiphyData
import com.kotlinplaygroundmvvm.ui.util.BasePresenter

interface GiphyListContract {
    interface View{
        fun onTrendingLoaded(list: List<GiphyData>)
        fun onSearchResultLoaded(searchResult: List<GiphyData>)
        fun onError()
    }

    interface Presenter: BasePresenter{
        fun getTrendingGiphyList(offset: Int)
        fun searchGiphy(query: String, offset: Int)
    }
}
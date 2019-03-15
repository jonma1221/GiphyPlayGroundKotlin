package com.kotlinplayground.ui.giphylist

import com.kotlinplayground.data.model.GiphyData
import com.kotlinplayground.ui.util.BasePresenter

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
package com.kotlinplaygroundmvvm.ui.giphylist.deprecated

import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.ui.util.BasePresenter

interface GiphyListContract {
    interface View{
        fun onTrendingLoaded(list: List<GiphyObject>)
        fun onSearchResultLoaded(searchResult: List<GiphyObject>)
        fun onError()
    }

    interface Presenter: BasePresenter{
        fun getTrendingGiphyList(offset: Int)
        fun searchGiphy(query: String, offset: Int)
    }
}
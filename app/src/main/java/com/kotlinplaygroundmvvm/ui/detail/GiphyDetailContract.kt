package com.kotlinplaygroundmvvm.ui.detail

import com.kotlinplaygroundmvvm.data.model.GiphyData
import com.kotlinplaygroundmvvm.ui.util.BasePresenter

interface GiphyDetailContract {
    interface View{
        fun onGiphyRetrieved(giphyData: GiphyData)
    }

    interface Presenter: BasePresenter{
        fun getGiphyById(id: String)
    }
}
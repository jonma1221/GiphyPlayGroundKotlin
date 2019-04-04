package com.kotlinplaygroundmvvm.ui.detail

import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.ui.util.BasePresenter

interface GiphyDetailContract {
    interface View{
        fun onGiphyRetrieved(giphyObject: GiphyObject)
    }

    interface Presenter: BasePresenter{
        fun getGiphyById(id: String)
    }
}
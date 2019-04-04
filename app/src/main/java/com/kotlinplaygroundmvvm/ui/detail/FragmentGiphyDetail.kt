package com.kotlinplaygroundmvvm.ui.detail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinplaygroundmvvm.R
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.ui.util.RxBus
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_giphy_detail.*

class FragmentGiphyDetail: Fragment(), GiphyDetailContract.View {

    lateinit var d: Disposable
    companion object {
        fun newInstance(id: String): FragmentGiphyDetail{
            val f = FragmentGiphyDetail()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_giphy_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        retrieveGiphyByRx()
    }

    private fun retrieveGiphyByRx() {
        d = RxBus.register(GiphyObject::class.java)
            .subscribe { giphyData -> onGiphyRetrieved(giphyData)}
    }
    override fun onGiphyRetrieved(giphyObject: GiphyObject) {
        fragment_giphy_detail_custom_view.mGiphyObject = giphyObject
        fragment_giphy_detail_custom_view.loadImage(giphyObject.images.downsized!!.url)
    }

    override fun onDestroy() {
        super.onDestroy()
        d.dispose()
    }
}
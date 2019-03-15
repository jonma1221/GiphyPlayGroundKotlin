package com.kotlinplayground.ui.giphylist

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SearchView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kotlinplayground.R
import com.kotlinplayground.data.model.GiphyData
import com.kotlinplayground.data.source.GiphyDataSourceImpl
import com.kotlinplayground.ui.util.Schedulers.SchedulerProviderImpl
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_giphy_list.*
import java.util.*
import java.util.concurrent.TimeUnit

class FragmentGiphyList: Fragment(), GiphyListContract.View {

    private lateinit var giphyListAdapter: GiphyListAdapter
    private lateinit var mPresenter: GiphyListPresenter
    private lateinit var publishSubject: PublishSubject<String>

    companion object {
        fun newInstance(): FragmentGiphyList {
            val f = FragmentGiphyList()
            val args = Bundle()
            f.arguments = args
            return f
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
            = inflater.inflate(R.layout.fragment_giphy_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        giphyListAdapter = GiphyListAdapter(ArrayList(0))
        fragment_giphy_list_rv.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = giphyListAdapter
        }

        setUpSearchPublishSubject()
        setupPresenter()

        fragment_giphy_list_searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(s: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String?): Boolean {
                if (s.isNullOrEmpty()) {
//                    shouldReplace = true
//                    searchTrending = true
                    mPresenter.getTrendingGiphyList(0)
                } else {
//                    shouldReplace = true
//                    searchTrending = false
                    publishSubject.onNext(s) // emit when text changes
                }
                return false
            }
        })
    }

    private fun setUpSearchPublishSubject() {
        publishSubject = PublishSubject.create()
        publishSubject.debounce (400, TimeUnit.MILLISECONDS)
            .subscribeOn(SchedulerProviderImpl().io())
            .observeOn(SchedulerProviderImpl().main())
            .doOnNext {showProgress()}
            .subscribe(object: Observer<String>{
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(s: String) {
                    mPresenter.searchGiphy(s, 0)
                }

                override fun onError(e: Throwable) {

                }
            })


    }

    fun showProgress(){

    }

    private fun setupPresenter() {
        mPresenter = GiphyListPresenter(GiphyDataSourceImpl(), SchedulerProviderImpl(), this)
        mPresenter.getTrendingGiphyList(0)
    }

    override fun onTrendingLoaded(list: List<GiphyData>) {
        giphyListAdapter.add(list)
    }

    override fun onSearchResultLoaded(searchResult: List<GiphyData>) {
        giphyListAdapter.replace(searchResult)
    }

    override fun onError() {

    }
}
package com.kotlinplayground.ui.giphylist

import com.kotlinplayground.data.source.GiphyDataSource
import com.kotlinplayground.ui.util.Schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class GiphyListPresenter(
    private val mGiphyDataSource: GiphyDataSource,
    private val mSchedulerProvider: SchedulerProvider,
    private val mGiphyListView: GiphyListContract.View)
    : GiphyListContract.Presenter {

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getTrendingGiphyList(offset: Int) {
        compositeDisposable.clear()
        val disposable = mGiphyDataSource.getTrending(offset)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
            .subscribe(
                { mGiphyListView.onTrendingLoaded(it) },
                { error -> mGiphyListView.onError()}
            )
        compositeDisposable.addAll(disposable)
    }

    override fun searchGiphy(query: String, offset: Int) {
        compositeDisposable.clear()
        val disposable = mGiphyDataSource.searchGiphy(query, offset)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
            .subscribe(
                { mGiphyListView.onSearchResultLoaded(it) },
                { error -> mGiphyListView.onError() })
        compositeDisposable.addAll(disposable)
    }

    override fun destroyView() {
        compositeDisposable.clear()
    }
}
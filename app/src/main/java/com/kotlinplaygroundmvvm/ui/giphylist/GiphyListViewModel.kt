package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.kotlinplaygroundmvvm.data.model.GiphyData
import com.kotlinplaygroundmvvm.data.source.GiphyRepository
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

class GiphyListViewModel(val giphyRepository: GiphyRepository,
                         val mSchedulerProvider: SchedulerProvider): ViewModel() {

    private val giphyList = MutableLiveData<List<GiphyData>>()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getTrendingGiphyList(offset: Int) {
        compositeDisposable.clear()

        val disposable = giphyRepository.getTrending(offset)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
            .subscribe(
                { data -> giphyList.value = data },
                { error -> }
            )
        compositeDisposable.addAll(disposable)
    }

    fun searchGiphy(query: String, offset: Int) {
        compositeDisposable.clear()
        val disposable = giphyRepository.searchGiphy(query, offset)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
            .subscribe(
                { giphyList.value = it },
                { error ->  })
        compositeDisposable.addAll(disposable)
    }

    fun getGiphyListLiveData(): MutableLiveData<List<GiphyData>> = giphyList

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
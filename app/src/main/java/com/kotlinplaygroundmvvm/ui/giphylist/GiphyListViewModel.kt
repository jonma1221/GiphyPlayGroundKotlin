package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GiphyListViewModel @Inject constructor(val giphyRepository: GiphyRepository): ViewModel() {

    private val giphyList = MutableLiveData<List<GiphyObject>>()

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getTrendingGiphyList(offset: Int) {
        compositeDisposable.clear()

        val disposable = giphyRepository.getTrending(offset)
            .subscribe(
                { data -> giphyList.postValue(data)},
                { error -> Log.d("here", error.localizedMessage)}
            )
        compositeDisposable.addAll(disposable)
    }

    fun searchGiphy(query: String, offset: Int) {
        compositeDisposable.clear()
        val disposable = giphyRepository.searchGiphy(query, offset)
            .subscribe(
                { giphyList.value = it },
                { error ->  })
        compositeDisposable.addAll(disposable)
    }

    fun getGiphyListLiveData(): MutableLiveData<List<GiphyObject>> = giphyList

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
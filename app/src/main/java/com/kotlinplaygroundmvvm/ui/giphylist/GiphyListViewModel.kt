package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GiphyListViewModel @Inject constructor(val giphyRepository: GiphyRepository): ViewModel() {

    private val _giphyList = MutableLiveData<List<GiphyObject>>()
    val giphyList: LiveData<List<GiphyObject>> = _giphyList

    val compositeDisposable: CompositeDisposable = CompositeDisposable()

    fun getTrendingGiphyList(offset: Int) {
        compositeDisposable.clear()

        val disposable = giphyRepository.getTrending(offset)
            .subscribe(
                { data -> _giphyList.postValue(data)},
                { error -> }
            )
        compositeDisposable.addAll(disposable)
    }

    fun searchGiphy(query: String, offset: Int) {
        compositeDisposable.clear()
        val disposable = giphyRepository.searchGiphy(query, offset)
            .subscribe(
                { _giphyList.value = it },
                { error ->  })
        compositeDisposable.addAll(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
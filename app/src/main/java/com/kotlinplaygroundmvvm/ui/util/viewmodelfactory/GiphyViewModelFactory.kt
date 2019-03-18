package com.kotlinplaygroundmvvm.ui.util.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kotlinplaygroundmvvm.data.source.GiphyRepository
import com.kotlinplaygroundmvvm.ui.giphylist.GiphyListViewModel
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl

class GiphyViewModelFactory: ViewModelProvider.Factory {
    //todo - better way to inject repository and scheduler
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GiphyListViewModel(GiphyRepository(), SchedulerProviderImpl()) as T
    }
}
package com.kotlinplaygroundmvvm.ui.util.viewmodelfactory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSourceImpl
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepositoryImpl
import com.kotlinplaygroundmvvm.network.RetrofitClientInstance
import com.kotlinplaygroundmvvm.network.service.GiphyGetService
import com.kotlinplaygroundmvvm.ui.giphylist.GiphyListViewModel
import com.kotlinplaygroundmvvm.ui.util.App
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl
import javax.inject.Inject

class GiphyViewModelFactory @Inject constructor(): ViewModelProvider.Factory {
    //todo - better way to inject repository and scheduler
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        return GiphyListViewModel(GiphyRepositoryImpl(SchedulerProviderImpl())) as T
//    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var giphyGetService: GiphyGetService = RetrofitClientInstance.retrofit.create(GiphyGetService::class.java)
        val remoteDataSource: GiphyDataSource = GiphyDataSourceImpl(giphyGetService)
        val db = GiphyDataBase.getInstance(App.applicationContext())
        return GiphyListViewModel(GiphyRepositoryImpl(remoteDataSource, db, SchedulerProviderImpl())) as T
    }
}
package com.kotlinplaygroundmvvm.di

import android.arch.lifecycle.ViewModel
import com.kotlinplaygroundmvvm.ui.giphylist.GiphyListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface GiphyListViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(GiphyListViewModel::class)
    fun providesGiphyListViewModel(model: GiphyListViewModel): ViewModel
}
package com.kotlinplaygroundmvvm.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.kotlinplaygroundmvvm.ui.giphylist.GiphyListViewModel
import com.kotlinplaygroundmvvm.ui.util.viewmodelfactory.GiphyViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelFactoryModule {

    @Binds
    fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

}
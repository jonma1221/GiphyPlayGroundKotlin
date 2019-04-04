package com.kotlinplaygroundmvvm.di.components

import com.kotlinplaygroundmvvm.di.*
import com.kotlinplaygroundmvvm.ui.giphylist.FragmentGiphyList
import com.kotlinplaygroundmvvm.ui.util.App
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    NetworkModule::class,
    LocalSourceModule::class,
    RepositoryModule::class,
    ViewModelFactoryModule::class,
    GiphyListViewModelModule::class])
interface ApplicationComponent {
    fun inject(application: App)
    fun inject(f: FragmentGiphyList)
}
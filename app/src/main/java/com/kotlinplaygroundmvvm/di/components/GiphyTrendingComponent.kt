package com.kotlinplaygroundmvvm.di.components

import com.kotlinplaygroundmvvm.di.components.ApplicationComponent
import com.kotlinplaygroundmvvm.di.scopes.FragmentScope
import com.kotlinplaygroundmvvm.ui.giphylist.FragmentGiphyList
import dagger.Component

@FragmentScope
@Component(dependencies = [ApplicationComponent::class], modules = [])
interface GiphyTrendingComponent {

    fun inject(fragment: FragmentGiphyList)
}
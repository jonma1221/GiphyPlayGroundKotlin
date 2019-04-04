package com.kotlinplaygroundmvvm.ui.util

import android.app.Application
import android.util.Log
import com.facebook.stetho.Stetho
import com.kotlinplaygroundmvvm.di.AppModule
import com.kotlinplaygroundmvvm.di.LocalSourceModule
import com.kotlinplaygroundmvvm.di.NetworkModule
import com.kotlinplaygroundmvvm.di.RepositoryModule
import com.kotlinplaygroundmvvm.di.components.ApplicationComponent
import com.kotlinplaygroundmvvm.di.components.DaggerApplicationComponent

class App: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        context = this
        Stetho.initializeWithDefaults(this)
        component = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
//            .localSourceModule(LocalSourceModule()) // if a module has no arg constructor, don't have to instantiate
//            .networkModule(NetworkModule())
//            .repositoryModule(RepositoryModule())
            .build()
        component.inject(this)
    }

    companion object {
        var context: App? = null

        fun applicationContext() : App {
            return context as App
        }
    }
}
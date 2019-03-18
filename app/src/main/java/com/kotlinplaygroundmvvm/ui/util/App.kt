package com.kotlinplaygroundmvvm.ui.util

import android.app.Application
import android.util.Log

class App: Application() {

    override fun onCreate() {
        context = this
        Log.v("here", "here")
        super.onCreate()
    }

    companion object {
        var context: App? = null

        fun applicationContext() : App {
            return context as App
        }
    }
}
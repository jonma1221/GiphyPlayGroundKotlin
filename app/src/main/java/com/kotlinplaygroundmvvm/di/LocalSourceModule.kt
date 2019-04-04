package com.kotlinplaygroundmvvm.di

import android.arch.persistence.room.Room
import android.content.Context
import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.db.GiphyObjDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalSourceModule {

    @Singleton
    @Provides
    fun providesGiphyDataBase(context: Context): GiphyDataBase{
        return Room.databaseBuilder(
            context.applicationContext,
            GiphyDataBase::class.java,
            "giphy_database"
        ).build()
    }
}
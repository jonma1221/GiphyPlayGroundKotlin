package com.kotlinplaygroundmvvm.di

import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepository
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepositoryImpl
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProvider
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

//    @Singleton
//    @Provides
//    fun providesGiphyRepository(remoteDataSource: GiphyDataSource, db: GiphyDataBase,
//                                mSchedulerProvider: SchedulerProvider
//    ): GiphyRepository{
//        return GiphyRepositoryImpl(remoteDataSource, db, mSchedulerProvider)
//    }

    @Singleton
    @Provides
    fun providesGiphyRepository(giphyRepoImpl: GiphyRepositoryImpl): GiphyRepository{
        return giphyRepoImpl
    }

    @Singleton
    @Provides
    fun providesScheduler(): SchedulerProvider{
        return SchedulerProviderImpl()
    }
}
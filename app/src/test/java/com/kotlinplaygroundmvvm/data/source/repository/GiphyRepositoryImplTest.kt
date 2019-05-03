package com.kotlinplaygroundmvvm.data.source.repository

import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.db.GiphyObjDAO
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSourceImpl
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProvider
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderImpl
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderTrampoline
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class GiphyRepositoryImplTest{

    lateinit var repositoryImpl: GiphyRepositoryImpl

    @Mock
    lateinit var remoteDataSource: GiphyDataSource

    @Mock
    lateinit var db: GiphyDataBase

    @Mock
    lateinit var dao: GiphyObjDAO

    lateinit var schedulerProviderImpl: SchedulerProvider

    @Before
    fun init(){
        MockitoAnnotations.initMocks(this)
        schedulerProviderImpl = SchedulerProviderTrampoline()
        repositoryImpl = GiphyRepositoryImpl(remoteDataSource, db, schedulerProviderImpl)
    }

    @Test
    fun getTrendingApi_success(){
        // mock the api response
        `when`(remoteDataSource.getTrending(1)).thenReturn(
            Single.just(ArgumentMatchers.anyList<GiphyObject>()))

        repositoryImpl.getTrendingFromApi(1)
        verify(remoteDataSource).getTrending(1)
    }

    @Test
    fun getTrendingDb_success(){
        // mock the results from the dao query when getTrendingFromDb is called
        `when`(db.giphyObjDao()).thenReturn(dao)
        val data = listOf<GiphyObject>()
        `when`(db.giphyObjDao().getGiphyList()).thenReturn(
            Single.just(data))

        repositoryImpl.getTrendingFromDB()
        verify(dao).getGiphyList()
    }

    @Test
    fun searchGiphy_success(){
        `when`(remoteDataSource.searchGiphy("any",1)).thenReturn(
            Single.just(ArgumentMatchers.anyList<GiphyObject>()))

        repositoryImpl.searchGiphy("any", 1)
    }
}
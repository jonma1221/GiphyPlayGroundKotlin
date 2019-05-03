package com.kotlinplaygroundmvvm.ui.giphylist

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.db.GiphyObjDAO
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepository
import com.kotlinplaygroundmvvm.data.source.repository.GiphyRepositoryImpl
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProvider
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProviderTrampoline
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class GiphyListViewModelTest {

    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    lateinit var repository: GiphyRepository
    lateinit var viewModel: GiphyListViewModel

    @Mock
    lateinit var remoteDataSource: GiphyDataSource

    @Mock
    lateinit var db: GiphyDataBase

    @Mock
    lateinit var dao: GiphyObjDAO

    lateinit var schedulerProviderImpl: SchedulerProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        schedulerProviderImpl = SchedulerProviderTrampoline()
        repository = GiphyRepositoryImpl(remoteDataSource, db, schedulerProviderImpl)
        viewModel = GiphyListViewModel(repository)
    }

    @Test
    fun getTrendingGiphyListSuccess() {
//        `when`(repository.getTrending(1))
//            .thenReturn(Observable.just(ArgumentMatchers.anyList<GiphyObject>()))
        val data = listOf<GiphyObject>()
        `when`(remoteDataSource.getTrending(1)).thenReturn(
            Single.just(data))
        `when`(db.giphyObjDao()).thenReturn(dao)
        `when`(db.giphyObjDao().getGiphyList()).thenReturn(
            Single.just(data))

        // mock observer and let viewmodel observe
        val observer =  mock(Observer::class.java) as Observer<List<GiphyObject>>
        viewModel.getGiphyListLiveData().observeForever(observer)
        viewModel.getTrendingGiphyList(1)
//        verify(observer).onChanged(data)
        assertNotNull(viewModel.getGiphyListLiveData().value)
    }

    @Test
    fun getTrendingGiphyListError() {
        val data = listOf<GiphyObject>()
        `when`(remoteDataSource.getTrending(1)).thenReturn(
            Single.just(data))
        `when`(db.giphyObjDao()).thenReturn(dao)
        `when`(db.giphyObjDao().getGiphyList()).thenReturn(
            Single.error(Throwable()))

        // mock observer and let viewmodel observe
        val observer =  mock(Observer::class.java) as Observer<List<GiphyObject>>
        viewModel.getGiphyListLiveData().observeForever(observer)
        viewModel.getTrendingGiphyList(1)
        verify(observer, never()).onChanged(ArgumentMatchers.any())
    }

    @Test
    fun searchGiphy() {
    }
}
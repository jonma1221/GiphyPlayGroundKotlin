package com.kotlinplaygroundmvvm.data.source.repository

import com.kotlinplaygroundmvvm.data.db.GiphyDataBase
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSource
import com.kotlinplaygroundmvvm.data.source.network.GiphyDataSourceImpl
import com.kotlinplaygroundmvvm.ui.util.App
import com.kotlinplaygroundmvvm.ui.util.Schedulers.SchedulerProvider
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class GiphyRepositoryImpl @Inject constructor(
    private val remoteDataSource: GiphyDataSource,
    private val db: GiphyDataBase,
    private val mSchedulerProvider: SchedulerProvider) : GiphyRepository {

//    val remoteDataSource: GiphyDataSource =
//        GiphyDataSourceImpl()
//    val db =
//        GiphyDataBase.getInstance(App.applicationContext())

    override fun getGiphy(id: String): Single<GiphyObject> {
        return remoteDataSource.getGiphy(id)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
    }

    override fun getTrending(offset: Int): Observable<List<GiphyObject>> {
        val trendingApi = getTrendingFromApi(offset)
        val trendingDb = getTrendingFromDB()
        return Observable.concatArrayEager(trendingDb, trendingApi)
    }

    override fun searchGiphy(query: String, offset: Int): Single<List<GiphyObject>> {
        return remoteDataSource.searchGiphy(query, offset)
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
    }

    fun insertGiphyList(data: List<GiphyObject>){
        Completable.fromCallable { db.giphyObjDao().insertGiphyList(data) }
            .subscribeOn(mSchedulerProvider.io())
            .subscribe()
    }

    fun getTrendingFromApi(offset: Int): Observable<List<GiphyObject>>{
        return remoteDataSource.getTrending(offset)
            .toObservable()
            .doOnNext{insertGiphyList(it)}
            .subscribeOn(mSchedulerProvider.io())
            .observeOn(mSchedulerProvider.main())
    }

    fun getTrendingFromDB(): Observable<List<GiphyObject>>{
        return db.giphyObjDao()
            .getGiphyList()
            .toObservable()
            .subscribeOn(mSchedulerProvider.io())

    }
}
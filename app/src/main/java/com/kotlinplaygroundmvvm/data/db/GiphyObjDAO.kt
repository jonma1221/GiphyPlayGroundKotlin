package com.kotlinplaygroundmvvm.data.db

import android.arch.persistence.room.*
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface GiphyObjDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertGiphyList(giphyList: List<GiphyObject>)

    @Query("SELECT * FROM GiphyObject")
    fun getGiphyList(): Single<List<GiphyObject>>

    @Query("DELETE FROM GiphyObject WHERE GiphyObject.id NOT IN(:giphyObj)")
    fun deleteGiphyById(giphyObj: List<String>)
}
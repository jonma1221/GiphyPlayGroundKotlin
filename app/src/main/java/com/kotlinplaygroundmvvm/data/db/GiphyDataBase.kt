package com.kotlinplaygroundmvvm.data.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject

@Database(entities = [GiphyObject::class], version = 1, exportSchema = false)
abstract class GiphyDataBase: RoomDatabase(){
    abstract fun giphyObjDao(): GiphyObjDAO

    companion object {
        private var INSTANCE: GiphyDataBase? = null

        fun getInstance(context: Context): GiphyDataBase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GiphyDataBase::class.java,
                    "giphy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
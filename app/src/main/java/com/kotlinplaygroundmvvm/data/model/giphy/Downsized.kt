package com.kotlinplaygroundmvvm.data.model.giphy

import android.arch.persistence.room.ColumnInfo

data class Downsized(val size: String,
                     val width: String,
                     @ColumnInfo(name = "downsized_url") val url: String,
                     val height: String)
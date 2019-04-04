package com.kotlinplaygroundmvvm.data.model.giphy

import android.arch.persistence.room.Embedded
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GiphyObject(
    @PrimaryKey val id: String,
    @Embedded val images: Images,
    @SerializedName("import_datetime") val importDatetime: String,
    @SerializedName("embed_url") val embedUrl: String,
    @SerializedName("trending_datetime") val trendingDatetime: String,
    @SerializedName("bitly_url") val bitlyUrl: String,
    @SerializedName("bitly_gif_url")val bitlyGifUrl: String,
    @SerializedName("source_tld")val sourceTld: String,
    @SerializedName("source_post_url")val sourcePostUrl: String,
    @SerializedName("content_url")val contentUrl: String,
    val rating: String,
    val source: String,
    val type: String,
    val title: String,
    val url: String,
    val slug: String,
    val username: String )
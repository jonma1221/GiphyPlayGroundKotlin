package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName

class GiphyData(
    @SerializedName("images") val giphyImages: GiphyImages,
    @SerializedName("title") val title: String,
    @SerializedName("id") val id: String
    )


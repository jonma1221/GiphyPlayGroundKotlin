package com.kotlinplayground.data.model

import com.google.gson.annotations.SerializedName

class GiphyData(
    @SerializedName("images") val giphyImages: GiphyImages,
    @SerializedName("title") val title: String)


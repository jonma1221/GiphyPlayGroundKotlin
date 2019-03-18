package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName

class ImageBase(
    @SerializedName("url")val url: String,
    @SerializedName("width")val width: String,
    @SerializedName("height")val height: String,
    @SerializedName("size") val size: String
)
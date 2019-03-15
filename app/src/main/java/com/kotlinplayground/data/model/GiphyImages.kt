package com.kotlinplayground.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GiphyImages(
    @SerializedName("downsized") val downsized: ImageBase,
    @SerializedName("downsized_large") val downsizedLarge: ImageBase
)
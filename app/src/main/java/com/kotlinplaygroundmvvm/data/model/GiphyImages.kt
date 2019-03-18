package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName

class GiphyImages(
    @SerializedName("downsized") val downsized: ImageBase,
    @SerializedName("downsized_large") val downsizedLarge: ImageBase
)
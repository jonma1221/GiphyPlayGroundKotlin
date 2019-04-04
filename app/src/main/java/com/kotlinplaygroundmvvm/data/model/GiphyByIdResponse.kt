package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject

class GiphyByIdResponse(
    @SerializedName("data") val giphyObject: GiphyObject
)
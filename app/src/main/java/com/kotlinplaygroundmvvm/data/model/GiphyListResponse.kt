package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName
import com.kotlinplaygroundmvvm.data.model.giphy.GiphyObject

class GiphyListResponse(
    @SerializedName("data") val list: MutableList<GiphyObject>
)
package com.kotlinplayground.data.model

import com.google.gson.annotations.SerializedName

class GiphyListResponse(
    @SerializedName("data") val list: MutableList<GiphyData>
)
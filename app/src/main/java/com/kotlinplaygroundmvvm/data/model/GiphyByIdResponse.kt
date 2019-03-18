package com.kotlinplaygroundmvvm.data.model

import com.google.gson.annotations.SerializedName

class GiphyByIdResponse(
    @SerializedName("data") val giphyData: GiphyData
)
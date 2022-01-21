package com.example.marvelapp.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataWrapperResponse(
    // data é um Objeto pois temos um array "{ ... } no corpo do Json no Objeto data"
    @SerializedName("copyright")
    val copyright: String,
    @SerializedName("data")
    val data: DataContainerResponse
)

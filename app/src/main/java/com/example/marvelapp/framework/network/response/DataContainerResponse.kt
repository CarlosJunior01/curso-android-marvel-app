package com.example.marvelapp.framework.network.response

import com.google.gson.annotations.SerializedName

data class DataContainerResponse(
    // results é um List pois temos um array "[ ... ] no corpo do Json no Array results"
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("total")
    val total: Int,
    @SerializedName("results")
    val results: List<CharacterResponse>
)

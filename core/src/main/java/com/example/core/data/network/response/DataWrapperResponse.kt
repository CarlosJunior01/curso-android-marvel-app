package com.example.core.data.network.response

data class DataWrapperResponse(
    // data é um Objeto pois temos um array "{ ... } no corpo do Json no Objeto data"
    val copyright: String,
    val data: DataContainerResponse
)

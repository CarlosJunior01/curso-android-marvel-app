package com.example.marvelapp.framework.network.response

data class DataContainerResponse(
    // results é um List pois temos um array "[ ... ] no corpo do Json no Array results"
    val offset: Int,
    val total: Int,
    val results: List<CharacterResponse>
)

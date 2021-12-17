package com.example.core.data.network.response

data class DataContainerResponse(
    // results é um List pois temos um array "[ ... ] no corpo do Json no Array results"
    val results: List<CharacterResponse>
)

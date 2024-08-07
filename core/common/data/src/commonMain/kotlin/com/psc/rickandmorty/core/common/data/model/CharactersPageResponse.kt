package com.psc.rickandmorty.core.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharactersPageResponse(
    @SerialName("results")
    val results: List<CharacterResponse>
)
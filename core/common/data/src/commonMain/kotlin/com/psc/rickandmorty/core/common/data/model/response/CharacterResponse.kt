package com.psc.rickandmorty.core.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("status")
    val status: String,

    @SerialName("species")
    val species: String,

    @SerialName("type")
    val type: String,

    @SerialName("gender")
    val gender: String,

    @SerialName("origin")
    val originReference: LocationReferenceResponse,

    @SerialName("location")
    val lastKnownLocationReference: LocationReferenceResponse,

    @SerialName("image")
    val image: String,

    @SerialName("episode")
    val episodesReferences: List<String>,
)
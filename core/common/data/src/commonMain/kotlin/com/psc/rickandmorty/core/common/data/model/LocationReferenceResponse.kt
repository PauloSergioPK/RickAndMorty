package com.psc.rickandmorty.core.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationReferenceResponse(
    @SerialName("name")
    val name: String,

    @SerialName("url")
    val url: String,
)
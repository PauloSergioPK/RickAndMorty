package com.psc.rickandmorty.core.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationsPageResponse(
    @SerialName("results")
    val results: List<LocationResponse>
)
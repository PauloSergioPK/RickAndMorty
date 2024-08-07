package com.psc.rickandmorty.core.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EpisodesPageResponse(
    @SerialName("results")
    val results: List<EpisodeResponse>
)
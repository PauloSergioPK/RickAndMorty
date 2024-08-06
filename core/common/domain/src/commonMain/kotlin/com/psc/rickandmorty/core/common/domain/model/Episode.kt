package com.psc.rickandmorty.core.common.domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: Int,
    val name: String,
    val airDate: LocalDate,
    val seasonAndEpisode: String
)
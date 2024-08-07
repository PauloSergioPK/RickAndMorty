package com.psc.rickandmorty.core.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Character(
    val id: Int,
    val name: String,
    val status: CharacterStatus,
    val species: String,
    val type: String,
    val gender: CharacterGender,
    val origin: Location?,
    val lastKnownLocation: Location?,
    val image: String,
    val episodes: List<Episode>
)
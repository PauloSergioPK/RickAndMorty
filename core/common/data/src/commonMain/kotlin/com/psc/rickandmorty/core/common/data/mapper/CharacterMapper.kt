package com.psc.rickandmorty.core.common.data.mapper

import com.psc.rickandmorty.core.common.data.model.response.CharacterResponse
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharacterGender
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.model.Location

private const val ALIVE = "Alive"
private const val DEAD = "Dead"

private const val MALE = "Male"
private const val FEMALE = "Female"
private const val GENDERLESS = "Genderless"

fun CharacterResponse.toCharacter(
    originLocation: Location,
    lastKnownLocation: Location,
    episodes: List<Episode>
): Character {
    return Character(
        id = id,
        name = name,
        status = status.toCharacterStatus(),
        species = species,
        type = type,
        gender = status.toCharacterGender(),
        origin = originLocation,
        lastKnownLocation = lastKnownLocation,
        image = image,
        episodes = episodes,
    )
}

private fun String.toCharacterStatus(): CharacterStatus {
    return when (this) {
        ALIVE -> CharacterStatus.ALIVE
        DEAD -> CharacterStatus.DEAD
        else -> CharacterStatus.UNKNOWN
    }
}

private fun String.toCharacterGender(): CharacterGender {
    return when (this) {
        MALE -> CharacterGender.MALE
        FEMALE -> CharacterGender.FEMALE
        GENDERLESS -> CharacterGender.GENDERLESS
        else -> CharacterGender.UNKNOWN
    }
}
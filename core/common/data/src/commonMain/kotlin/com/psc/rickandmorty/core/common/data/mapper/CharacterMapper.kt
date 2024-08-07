package com.psc.rickandmorty.core.common.data.mapper

import com.psc.rickandmorty.core.common.data.model.CharacterResponse
import com.psc.rickandmorty.core.common.data.model.EpisodeResponse
import com.psc.rickandmorty.core.common.data.model.LocationResponse
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharacterGender
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus

private const val ALIVE = "Alive"
private const val DEAD = "Dead"

private const val MALE = "Male"
private const val FEMALE = "Female"
private const val GENDERLESS = "Genderless"

fun CharacterResponse.toCharacter(
    originLocationResponse: LocationResponse,
    lastKnownLocationResponse: LocationResponse,
    episodesResponses: List<EpisodeResponse>
): Character {
    return Character(
        id = id,
        name = name,
        status = status.toCharacterStatus(),
        species = species,
        type = type,
        gender = status.toCharacterGender(),
        origin = originLocationResponse.toLocation(),
        lastKnownLocation = lastKnownLocationResponse.toLocation(),
        image = image,
        episodes = episodesResponses.map { it.toEpisode() },
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
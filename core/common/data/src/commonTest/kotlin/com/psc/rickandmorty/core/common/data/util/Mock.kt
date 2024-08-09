package com.psc.rickandmorty.core.common.data.util

import com.psc.rickandmorty.core.common.data.model.response.CharacterResponse
import com.psc.rickandmorty.core.common.data.model.response.EpisodeResponse
import com.psc.rickandmorty.core.common.data.model.response.LocationReferenceResponse
import com.psc.rickandmorty.core.common.data.model.response.LocationResponse
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharacterGender
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus
import com.psc.rickandmorty.core.common.domain.model.CharactersPage
import com.psc.rickandmorty.core.common.domain.model.Episode
import com.psc.rickandmorty.core.common.domain.model.Location
import kotlinx.datetime.LocalDate

object Mock {
    val location = Location(
        id = 1,
        name = "Earth (C-137)",
        type = "Planet",
        dimension = "Dimension C-137"
    )

    val episode = Episode(
        id = 1,
        name = "Pilot",
        airDate = LocalDate(2013, 12, 2),
        seasonAndEpisode = "S01E01"
    )

    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "",
        gender = CharacterGender.MALE,
        origin = location,
        lastKnownLocation = location,
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodes = listOf(episode)
    )

    val charactersPage = CharactersPage(
        page = 1,
        remainingPages = 0,
        characters = listOf(character)
    )

    internal val characterResponse = CharacterResponse(
        id = 1,
        name = "Rick Sanchez",
        status = "Alive",
        species = "Human",
        type = "",
        gender = "Male",
        originReference = LocationReferenceResponse(
            name = "Earth",
            url = "https://rickandmortyapi.com/api/location/1"
        ),
        lastKnownLocationReference = LocationReferenceResponse(
            name = "Earth",
            url = "https://rickandmortyapi.com/api/location/20"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodesReferences = listOf("https://rickandmortyapi.com/api/episode/1")
    )

    internal val episodeResponse = EpisodeResponse(
        id = 1,
        name = "Pilot",
        airDate = "December 2, 2013",
        seasonAndEpisode = "S01E01"
    )

    internal val locationResponse = LocationResponse(
        id = 1,
        name = "Earth (C-137)",
        type = "Planet",
        dimension = "Dimension C-137"
    )
}
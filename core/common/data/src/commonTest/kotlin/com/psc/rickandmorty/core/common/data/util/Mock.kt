package com.psc.rickandmorty.core.common.data.util

import com.psc.rickandmorty.core.common.data.mapper.toEpisodeDto
import com.psc.rickandmorty.core.common.data.model.response.EpisodeResponse
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
    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "",
        gender = CharacterGender.MALE,
        origin = location,
        lastKnownLocation = Location(
            id = 20,
            name = "Earth (Replacement Dimension)",
            type = "Planet",
            dimension = "Replacement Dimension)"
        ),
        image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
        episodes = listOf()
    )
    val charactersPage = CharactersPage(
        page = 1,
        remainingPages = 0,
        characters = listOf(character)
    )
    val episode = Episode(
        id = 1,
        name = "Pilot",
        airDate = LocalDate(2013, 12, 2),
        seasonAndEpisode = "S01E01"
    )
    internal val episodeDto = episode.toEpisodeDto()
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
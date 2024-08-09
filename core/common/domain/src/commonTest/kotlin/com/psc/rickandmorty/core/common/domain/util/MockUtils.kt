package com.psc.rickandmorty.core.common.domain.util

import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.model.CharacterGender
import com.psc.rickandmorty.core.common.domain.model.CharacterStatus
import com.psc.rickandmorty.core.common.domain.model.CharactersPage
import com.psc.rickandmorty.core.common.domain.model.Location

object MockUtils {
    val character = Character(
        id = 1,
        name = "Rick Sanchez",
        status = CharacterStatus.ALIVE,
        species = "Human",
        type = "",
        gender = CharacterGender.MALE,
        origin = Location(
            id = 1,
            name = "Earth (C-137)",
            type = "Planet",
            dimension = "Dimension C-137"
        ),
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
}
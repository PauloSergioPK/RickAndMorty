package com.psc.rickandmorty.core.common.domain.model

data class CharactersPage(
    val page: Int,
    val remainingPages: Int,
    val characters: List<Character>
)
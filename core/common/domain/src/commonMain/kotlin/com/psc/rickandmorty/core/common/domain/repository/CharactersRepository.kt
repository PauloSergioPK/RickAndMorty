package com.psc.rickandmorty.core.common.domain.repository

import com.psc.rickandmorty.core.common.domain.model.CharactersPage

interface CharactersRepository {
    suspend fun getPage(page: Int): Result<CharactersPage>
}
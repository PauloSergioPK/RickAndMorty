package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.domain.model.Character

interface CharactersRemoteDataSource {
    suspend fun getPage(page: Int): List<Character>
}
package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.domain.model.CharactersPage

interface CharactersRemoteDataSource {
    suspend fun getPage(page: Int): CharactersPage
}
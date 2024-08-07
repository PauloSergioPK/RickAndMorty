package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.CharactersPageResponse

interface CharactersService {
    suspend fun getPage(page: Int): CharactersPageResponse
}
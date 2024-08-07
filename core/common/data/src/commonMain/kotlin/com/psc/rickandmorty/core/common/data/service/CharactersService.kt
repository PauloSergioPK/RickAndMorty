package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.CharactersPageResponse

interface CharactersService {
    suspend fun getPage(page: Int): CharactersPageResponse
}
package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.response.EpisodesPageResponse

interface EpisodeService {
    suspend fun getPage(page: Int): EpisodesPageResponse
}
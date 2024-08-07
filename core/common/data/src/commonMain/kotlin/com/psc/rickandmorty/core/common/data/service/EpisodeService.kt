package com.psc.rickandmorty.core.common.data.service

import com.psc.rickandmorty.core.common.data.model.EpisodeResponse

interface EpisodeService {
    suspend fun getEpisode(episode: Int): EpisodeResponse
}
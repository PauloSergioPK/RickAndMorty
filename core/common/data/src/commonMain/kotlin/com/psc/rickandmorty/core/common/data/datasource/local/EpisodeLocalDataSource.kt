package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.domain.model.Episode

interface EpisodeLocalDataSource {
    suspend fun addEpisodes(episodes: List<Episode>)
    suspend fun getEpisodeById(id: Int): Episode
}
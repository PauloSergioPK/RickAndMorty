package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.domain.model.Episode

interface EpisodeRemoteDataSource {
    suspend fun getAllEpisodes(): List<Episode>
}
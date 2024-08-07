package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.EpisodeRemoteDataSource
import com.psc.rickandmorty.core.common.domain.repository.EpisodesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class EpisodesRepositoryImpl(
    private val remoteDataSource: EpisodeRemoteDataSource,
    private val localDataSource: EpisodeLocalDataSource
) : EpisodesRepository {


    override suspend fun fetchEpisodes(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val episodes = remoteDataSource.getAllEpisodes()
                localDataSource.addEpisodes(episodes)
            }
        }
    }
}
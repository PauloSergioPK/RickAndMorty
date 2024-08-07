package com.psc.rickandmorty.core.common.domain.repository

interface EpisodesRepository {
    suspend fun fetchEpisodes(): Result<Unit>
}
package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.repository.EpisodesRepository

internal class FetchEpisodesUseCaseImpl(
    private val repository: EpisodesRepository
) : FetchEpisodesUseCase {
    override suspend fun invoke(): Result<Unit> {
        return repository.fetchEpisodes()
    }

}
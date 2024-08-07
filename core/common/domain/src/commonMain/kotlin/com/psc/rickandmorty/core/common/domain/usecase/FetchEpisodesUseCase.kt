package com.psc.rickandmorty.core.common.domain.usecase

interface FetchEpisodesUseCase {
    suspend operator fun invoke(): Result<Unit>
}
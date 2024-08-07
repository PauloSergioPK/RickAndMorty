package com.psc.rickandmorty.core.common.domain.usecase

interface FetchLocationsUseCase {
    suspend operator fun invoke(): Result<Unit>
}
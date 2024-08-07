package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.repository.LocationsRepository

internal class FetchLocationsUseCaseImpl(
    private val repository: LocationsRepository
) : FetchLocationsUseCase {
    override suspend fun invoke(): Result<Unit> {
        return repository.fetchLocations()
    }

}
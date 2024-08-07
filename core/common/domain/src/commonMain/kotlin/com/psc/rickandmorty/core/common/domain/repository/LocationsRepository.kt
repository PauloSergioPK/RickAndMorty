package com.psc.rickandmorty.core.common.domain.repository

interface LocationsRepository {
    suspend fun fetchLocations(): Result<Unit>
}
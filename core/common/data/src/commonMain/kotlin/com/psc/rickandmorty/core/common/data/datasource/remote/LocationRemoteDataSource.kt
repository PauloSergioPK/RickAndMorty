package com.psc.rickandmorty.core.common.data.datasource.remote

import com.psc.rickandmorty.core.common.domain.model.Location

interface LocationRemoteDataSource {
    suspend fun getAllLocations(): List<Location>
}
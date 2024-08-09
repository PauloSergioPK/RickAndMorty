package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.domain.model.Location

interface LocationLocalDataSource {
    suspend fun addLocations(locations: List<Location>)
    suspend fun getLocationById(id: Int): Location?
}
package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSourceImpl
import com.psc.rickandmorty.core.common.data.datasource.remote.LocationRemoteDataSource
import com.psc.rickandmorty.core.common.domain.repository.LocationsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class LocationsRepositoryImpl(
    private val remoteDataSource: LocationRemoteDataSource,
    private val localDataSource: LocationLocalDataSourceImpl
) : LocationsRepository {


    override suspend fun fetchLocations(): Result<Unit> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val locations = remoteDataSource.getAllLocations()
                localDataSource.addLocations(locations)
            }
        }
    }
}
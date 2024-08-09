package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.data.mapper.toLocation
import com.psc.rickandmorty.core.common.data.mapper.toLocationDto
import com.psc.rickandmorty.core.common.data.model.dto.LocationDto
import com.psc.rickandmorty.core.common.domain.model.Location
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.firstOrNull

internal class LocationLocalDataSourceImpl(
    private val realm: Realm
) : LocationLocalDataSource {
    override suspend fun addLocations(locations: List<Location>) {
        realm.write {
            locations.forEach {
                copyToRealm(it.toLocationDto(), UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun getLocationById(id: Int): Location? {
        val locationDto = realm.query(
            clazz = LocationDto::class,
            query = "id == $0 LIMIT(1)",
            id
        ).find().asFlow().firstOrNull()?.list?.firstOrNull()

        return locationDto?.toLocation()
    }
}
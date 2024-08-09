package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.data.mapper.toLocation
import com.psc.rickandmorty.core.common.data.model.dto.LocationDto
import com.psc.rickandmorty.core.common.data.util.Mock
import com.psc.rickandmorty.core.common.domain.model.Location
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class LocationLocalDataSourceImplTest {

    private lateinit var realm: Realm
    private lateinit var datasource: LocationLocalDataSource

    @BeforeTest
    fun setUp() {
        runBlocking {
            val realmConfiguration = RealmConfiguration.Builder(
                schema = setOf(LocationDto::class)
            ).inMemory().name(REALM_NAME).build()
            realm = Realm.open(realmConfiguration)
            datasource = LocationLocalDataSourceImpl(realm)
            clearDatabase()
        }
    }

    private suspend fun clearDatabase() {
        realm.write { deleteAll() }
    }

    @AfterTest
    fun tearDown() {
        runBlocking {
            clearDatabase()
            realm.close()
        }
    }

    @Test
    fun `when calls addLocations then upsert on realm`() {
        runTest {
            val locations = listOf(Mock.location)

            datasource.addLocations(locations)
            val insertedLocations = getAllLocations()

            assertEquals(insertedLocations, locations)
        }
    }

    private fun getAllLocations(): List<Location> {
        val all = realm.query<LocationDto>().find()
        return all.toList().map { it.toLocation() }
    }

    @Test
    fun `when calls getLocationById and exists an location with given id then return it`() {
        runTest {
            val expected = Mock.location
            val id = expected.id
            val locations = listOf(expected)

            datasource.addLocations(locations)
            val result = datasource.getLocationById(id)

            assertEquals(result, expected)
        }
    }

    @Test
    fun `when calls getLocationById and doesn't exists an Location with given id then return null`() {
        runTest {
            val location = Mock.location
            val id = location.id.inc()
            val locations = listOf(location)

            datasource.addLocations(locations)
            val result = datasource.getLocationById(id)

            assertNull(result)
        }
    }

    private companion object {
        const val REALM_NAME = "Location_realm_test"
    }

}
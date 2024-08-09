package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.data.mapper.toEpisode
import com.psc.rickandmorty.core.common.data.model.dto.EpisodeDto
import com.psc.rickandmorty.core.common.data.util.MockUtils
import com.psc.rickandmorty.core.common.domain.model.Episode
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

class EpisodeLocalDataSourceImplTest {

    private lateinit var realm: Realm
    private lateinit var datasource: EpisodeLocalDataSource

    @BeforeTest
    fun setUp() {
        runBlocking {
            val realmConfiguration = RealmConfiguration.Builder(
                schema = setOf(EpisodeDto::class)
            ).inMemory().name(REALM_NAME).build()
            realm = Realm.open(realmConfiguration)
            datasource = EpisodeLocalDataSourceImpl(realm)
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
    fun `when calls addEpisodes then upsert on realm`() {
        runTest {
            val episodes = listOf(MockUtils.episode)

            datasource.addEpisodes(episodes)
            val insertedEpisodes = getAllEpisodes()

            assertEquals(insertedEpisodes, episodes)
        }
    }

    private fun getAllEpisodes(): List<Episode> {
        val all = realm.query<EpisodeDto>().find()
        return all.toList().map { it.toEpisode() }
    }

    @Test
    fun `when calls getEpisodeById and exists an episode with given id then return it`() {
        runTest {
            val expected = MockUtils.episode
            val id = expected.id
            val episodes = listOf(expected)

            datasource.addEpisodes(episodes)
            val result = datasource.getEpisodeById(id)

            assertEquals(result, expected)
        }
    }

    @Test
    fun `when calls getEpisodeById and doesn't exists an episode with given id then return null`() {
        runTest {
            val episode = MockUtils.episode
            val id = episode.id.inc()
            val episodes = listOf(episode)

            datasource.addEpisodes(episodes)
            val result = datasource.getEpisodeById(id)

            assertNull(result)
        }
    }

    private companion object {
        const val REALM_NAME = "episode_realm_test"
    }

}
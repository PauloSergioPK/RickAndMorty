package com.psc.rickandmorty.core.common.data.datasource.local

import com.psc.rickandmorty.core.common.data.mapper.toEpisode
import com.psc.rickandmorty.core.common.data.mapper.toEpisodeDto
import com.psc.rickandmorty.core.common.data.model.dto.EpisodeDto
import com.psc.rickandmorty.core.common.domain.model.Episode
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.flow.first

internal class EpisodeLocalDataSourceImpl(
    private val realm: Realm
) : EpisodeLocalDataSource {

    override suspend fun addEpisodes(episodes: List<Episode>) {
        realm.write {
            episodes.forEach {
                copyToRealm(it.toEpisodeDto(), UpdatePolicy.ALL)
            }
        }
    }

    override suspend fun getEpisodeById(id: Int): Episode {
        val episodeDto = realm.query(
            clazz = EpisodeDto::class,
            query = "id == $0 LIMIT(1)",
            id
        ).find().asFlow().first().list.first()

        return episodeDto.toEpisode()
    }
}
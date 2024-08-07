package com.psc.rickandmorty.core.common.data.repository

import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSource
import com.psc.rickandmorty.core.common.domain.model.Character
import com.psc.rickandmorty.core.common.domain.repository.CharactersRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

internal class CharactersRepositoryImpl(
    private val remoteDataSource: CharactersRemoteDataSource
) : CharactersRepository {
    override suspend fun getPage(page: Int): Result<List<Character>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                remoteDataSource.getPage(page)
            }
        }
    }
}
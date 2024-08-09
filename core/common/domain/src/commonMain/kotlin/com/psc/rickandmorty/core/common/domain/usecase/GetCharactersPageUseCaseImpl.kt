package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.model.CharactersPage
import com.psc.rickandmorty.core.common.domain.repository.CharactersRepository

internal class GetCharactersPageUseCaseImpl(
    private val repository: CharactersRepository
): GetCharactersPageUseCase {
    override suspend fun invoke(page: Int): Result<CharactersPage> {
        return repository.getPage(page)
    }
}
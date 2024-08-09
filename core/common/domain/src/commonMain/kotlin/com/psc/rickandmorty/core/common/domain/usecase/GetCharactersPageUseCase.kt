package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.model.CharactersPage

interface GetCharactersPageUseCase {
    suspend operator fun invoke(page: Int): Result<CharactersPage>
}
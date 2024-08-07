package com.psc.rickandmorty.core.common.domain.usecase

import com.psc.rickandmorty.core.common.domain.model.Character

interface GetCharactersPageUseCase {
    suspend operator fun invoke(page: Int): Result<List<Character>>
}
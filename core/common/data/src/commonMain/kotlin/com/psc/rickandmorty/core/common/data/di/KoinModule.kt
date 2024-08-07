package com.psc.rickandmorty.core.common.data.di

import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.local.EpisodeLocalDataSourceImpl
import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSource
import com.psc.rickandmorty.core.common.data.datasource.local.LocationLocalDataSourceImpl
import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.CharactersRemoteDataSourceImpl
import com.psc.rickandmorty.core.common.data.datasource.remote.EpisodeRemoteDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.EpisodeRemoteDataSourceImpl
import com.psc.rickandmorty.core.common.data.datasource.remote.LocationRemoteDataSource
import com.psc.rickandmorty.core.common.data.datasource.remote.LocationRemoteDataSourceImpl
import com.psc.rickandmorty.core.common.data.model.dto.EpisodeDto
import com.psc.rickandmorty.core.common.data.model.dto.LocationDto
import com.psc.rickandmorty.core.common.data.provider.HttpClientProvider
import com.psc.rickandmorty.core.common.data.provider.HttpClientProviderImpl
import com.psc.rickandmorty.core.common.data.repository.CharactersRepositoryImpl
import com.psc.rickandmorty.core.common.data.repository.EpisodesRepositoryImpl
import com.psc.rickandmorty.core.common.data.repository.LocationsRepositoryImpl
import com.psc.rickandmorty.core.common.data.service.CharactersService
import com.psc.rickandmorty.core.common.data.service.CharactersServiceImpl
import com.psc.rickandmorty.core.common.data.service.EpisodeService
import com.psc.rickandmorty.core.common.data.service.EpisodeServiceImpl
import com.psc.rickandmorty.core.common.data.service.LocationService
import com.psc.rickandmorty.core.common.data.service.LocationServiceImpl
import com.psc.rickandmorty.core.common.domain.repository.CharactersRepository
import com.psc.rickandmorty.core.common.domain.repository.EpisodesRepository
import com.psc.rickandmorty.core.common.domain.repository.LocationsRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDataModule = module {
    single<Realm> {
        Realm.open(
            configuration = RealmConfiguration.Builder(
                schema = setOf(
                    EpisodeDto::class,
                    LocationDto::class
                )
            ).schemaVersion(0).compactOnLaunch().build()
        )
    }
    factoryOf(::CharactersRepositoryImpl) bind CharactersRepository::class
    factoryOf(::CharactersServiceImpl) bind CharactersService::class
    factoryOf(::CharactersRemoteDataSourceImpl) bind CharactersRemoteDataSource::class
    factoryOf(::HttpClientProviderImpl) bind HttpClientProvider::class
    factoryOf(::LocationServiceImpl) bind LocationService::class
    factoryOf(::EpisodeServiceImpl) bind EpisodeService::class
    factoryOf(::LocationLocalDataSourceImpl) bind LocationLocalDataSource::class
    factoryOf(::EpisodeLocalDataSourceImpl) bind EpisodeLocalDataSource::class
    factoryOf(::LocationRemoteDataSourceImpl) bind LocationRemoteDataSource::class
    factoryOf(::EpisodeRemoteDataSourceImpl) bind EpisodeRemoteDataSource::class
    factoryOf(::EpisodesRepositoryImpl) bind EpisodesRepository::class
    factoryOf(::LocationsRepositoryImpl) bind LocationsRepository::class
}
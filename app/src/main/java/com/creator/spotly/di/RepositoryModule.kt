package com.creator.spotly.di

import com.creator.spotly.api.geoapify.repository.PlacesRepository
import com.creator.spotly.api.geoapify.repository.PlacesRepositoryImpl
import com.creator.spotly.auth.repository.UserRepositoryImpl
import com.creator.spotly.auth.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindPlacesRepository(impl: PlacesRepositoryImpl): PlacesRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        impl: UserRepositoryImpl
    ): UserRepository
}
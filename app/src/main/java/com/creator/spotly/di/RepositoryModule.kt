package com.creator.spotly.di

import com.creator.spotly.data.repository.UserRepositoryImpl
import com.creator.spotly.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun bindMyRepository(impl: UserRepositoryImpl): UserRepository
}
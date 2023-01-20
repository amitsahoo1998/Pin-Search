package dev.amits.pinsearch.presentation.di

import dev.amits.pinsearch.data.repository.PinRepositoryImpl
import dev.amits.pinsearch.data.repository.datasource.LocalDataSource
import dev.amits.pinsearch.data.repository.datasource.OnlineDataSource
import dev.amits.pinsearch.domain.repository.PinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Provides
    @Singleton
    fun providePinRepository(onlineDataSource: OnlineDataSource,
    localDataSource: LocalDataSource) : PinRepository{
        return PinRepositoryImpl(onlineDataSource ,localDataSource)
    }

}
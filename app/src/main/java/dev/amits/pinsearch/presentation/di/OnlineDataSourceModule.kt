package dev.amits.pinsearch.presentation.di

import dev.amits.pinsearch.data.api.PinService
import dev.amits.pinsearch.data.repository.datasource.OnlineDataSource
import dev.amits.pinsearch.data.repository.datasourceimpl.OnlineDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OnlineDataSourceModule {

    @Singleton
    @Provides
    fun provideOnlineDataSource(pinService: PinService):OnlineDataSource{
        return OnlineDataSourceImpl(pinService)
    }

}
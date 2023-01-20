package dev.amits.pinsearch.presentation.di

import dev.amits.pinsearch.data.db.PinDao
import dev.amits.pinsearch.data.repository.datasource.LocalDataSource
import dev.amits.pinsearch.data.repository.datasourceimpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataSource(pinDao: PinDao) : LocalDataSource{
        return LocalDataSourceImpl(pinDao)
    }
}
package dev.amits.pinsearch.presentation.di

import dev.amits.pinsearch.presentation.view.pin.PinAdapter
import dev.amits.pinsearch.presentation.view.pin.PinDbAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AdapterModule {

    @Singleton
    @Provides
    fun providePinAdapter(): PinAdapter = PinAdapter()

    @Singleton
    @Provides
    fun providePinDbAdapter() : PinDbAdapter = PinDbAdapter()
}
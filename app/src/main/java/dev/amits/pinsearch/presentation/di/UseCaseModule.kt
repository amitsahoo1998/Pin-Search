package dev.amits.pinsearch.presentation.di

import dev.amits.pinsearch.domain.repository.PinRepository
import dev.amits.pinsearch.domain.usecases.GetSavedPinUseCases
import dev.amits.pinsearch.domain.usecases.PinResponseUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun providePinResponseUseCases(repository: PinRepository) : PinResponseUseCases {
        return PinResponseUseCases(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetSavedPinUseCases(repository: PinRepository) : GetSavedPinUseCases {
        return GetSavedPinUseCases(repository)
    }

}
package dev.amits.pinsearch.domain.usecases

import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.domain.repository.PinRepository
import kotlinx.coroutines.flow.Flow

class GetSavedPinUseCases (private val pinRepository: PinRepository) {
    fun execute() : Flow<List<PinCodeResponse.Hits.Hit.Source>> = pinRepository.getPinDataFromDb()
}
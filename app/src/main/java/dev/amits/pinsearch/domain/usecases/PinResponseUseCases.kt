package dev.amits.pinsearch.domain.usecases

import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.domain.repository.PinRepository
import dev.amits.pinsearch.utils.Resource

class PinResponseUseCases (private val repository: PinRepository) {
    suspend fun execute (pinCodeRequest: PinCodeRequest) : Resource<PinCodeResponse> =
        repository.getPinData(pinCodeRequest)
}
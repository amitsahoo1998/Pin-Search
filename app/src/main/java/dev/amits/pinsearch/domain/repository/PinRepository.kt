package dev.amits.pinsearch.domain.repository

import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PinRepository {
    suspend fun getPinData(pinCodeRequest: PinCodeRequest) : Resource<PinCodeResponse>
    fun getPinDataFromDb() : Flow<List<PinCodeResponse.Hits.Hit.Source>>
}
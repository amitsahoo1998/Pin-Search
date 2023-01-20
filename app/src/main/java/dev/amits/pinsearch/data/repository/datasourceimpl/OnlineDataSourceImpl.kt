package dev.amits.pinsearch.data.repository.datasourceimpl

import dev.amits.pinsearch.data.api.PinService
import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.data.repository.datasource.OnlineDataSource
import retrofit2.Response

class OnlineDataSourceImpl(private val pinService: PinService) : OnlineDataSource {
    override suspend fun getPinCodeData(pinCodeRequest: PinCodeRequest): Response<PinCodeResponse> {
        return pinService.getPinCodeData(pinCodeRequest)
    }
}
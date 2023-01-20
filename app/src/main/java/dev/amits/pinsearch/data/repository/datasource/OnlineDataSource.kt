package dev.amits.pinsearch.data.repository.datasource

import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import retrofit2.Response

interface OnlineDataSource {
    suspend fun getPinCodeData(pinCodeRequest: PinCodeRequest) : Response<PinCodeResponse>
}
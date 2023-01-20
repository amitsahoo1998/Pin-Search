package dev.amits.pinsearch.data.api




import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface PinService {

    @POST("hospicash_pincode_master/_search")
    suspend fun getPinCodeData(
        @Body pinCodeRequest: PinCodeRequest,
        @Header("Authorization") token: String = dev.amits.pinsearch.BuildConfig.API_KEY
    ): Response<PinCodeResponse>


}
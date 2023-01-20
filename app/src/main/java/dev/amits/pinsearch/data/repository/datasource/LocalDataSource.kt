package dev.amits.pinsearch.data.repository.datasource

import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getPinDb() : Flow<List<PinCodeResponse.Hits.Hit.Source>>
    suspend fun savePinDb(source : PinCodeResponse.Hits.Hit.Source)
    suspend fun saveAllPinData(pinList: List<PinCodeResponse.Hits.Hit.Source>)
}
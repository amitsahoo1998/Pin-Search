package dev.amits.pinsearch.data.repository


import dev.amits.pinsearch.data.model.pincode.PinCodeRequest
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.data.repository.datasource.LocalDataSource
import dev.amits.pinsearch.data.repository.datasource.OnlineDataSource
import dev.amits.pinsearch.domain.repository.PinRepository
import dev.amits.pinsearch.utils.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import retrofit2.Response

class PinRepositoryImpl(private val onlineDataSource: OnlineDataSource,
                        private val localDataSource: LocalDataSource
) : PinRepository {
    override suspend fun getPinData(pinCodeRequest: PinCodeRequest): Resource<PinCodeResponse> {
        return responseToResource(onlineDataSource.getPinCodeData(pinCodeRequest))
    }

    override fun getPinDataFromDb(): Flow<List<PinCodeResponse.Hits.Hit.Source>> {
        return localDataSource.getPinDb()
    }

    private fun responseToResource(response: Response<PinCodeResponse>) : Resource<PinCodeResponse> {
        if (response.isSuccessful){
            response.body()?.let {result->
                for (i in result.hits.hits){
                    CoroutineScope(Dispatchers.IO).launch {
                        localDataSource.savePinDb(i._source)
                    }
                }
                return Resource.Success(result)
            }
        }
        /*val jsonObject = response.errorBody()?.string()?.let { JSONObject(it) }
        val message = if (jsonObject?.has("statusDesc") == true) jsonObject.getString("statusDesc") else "${response.code()} Error"*/
        return Resource.Error(response.message())
    }
}
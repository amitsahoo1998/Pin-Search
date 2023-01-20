package dev.amits.pinsearch.data.repository.datasourceimpl

import dev.amits.pinsearch.data.db.PinDao
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import dev.amits.pinsearch.data.repository.datasource.LocalDataSource
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(private val pinDao: PinDao) : LocalDataSource {
    override fun getPinDb(): Flow<List<PinCodeResponse.Hits.Hit.Source>> {
        return pinDao.getPin()
    }

    override suspend fun savePinDb(source: PinCodeResponse.Hits.Hit.Source) = pinDao.saveSearchPin(source)

    override suspend fun saveAllPinData(pinList: List<PinCodeResponse.Hits.Hit.Source>) = pinDao.addAllPin(pinList)

}
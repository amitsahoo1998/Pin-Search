package dev.amits.pinsearch.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface PinDao {

    @Insert(onConflict = REPLACE)
    suspend fun saveSearchPin(source: PinCodeResponse.Hits.Hit.Source)

    @Insert(onConflict = REPLACE)
    suspend fun addAllPin(pinList: List<PinCodeResponse.Hits.Hit.Source>)

    @Query("SELECT * FROM PINTABLE")
    fun getPin() : Flow<List<PinCodeResponse.Hits.Hit.Source>>


}
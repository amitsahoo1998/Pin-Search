package dev.amits.pinsearch.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.amits.pinsearch.data.model.pincode.PinCodeResponse

@Database(entities = [PinCodeResponse.Hits.Hit.Source::class], exportSchema = false , version = 1)
abstract class PinDatabase : RoomDatabase() {
    abstract fun pinDao() : PinDao
}
package dev.amits.pinsearch.presentation.di

import android.content.Context
import androidx.room.Room
import dev.amits.pinsearch.data.db.PinDao
import dev.amits.pinsearch.data.db.PinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun providePinDatabase(@ApplicationContext context: Context) : PinDatabase{
        return Room.databaseBuilder(context , PinDatabase::class.java , "pinDb").fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun providePinDao(database: PinDatabase) : PinDao{
        return database.pinDao()
    }
}
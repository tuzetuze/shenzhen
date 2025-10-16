package com.shenzhen.housing.di

import android.content.Context
import androidx.room.Room
import com.shenzhen.housing.data.database.HousingDatabase
import com.shenzhen.housing.data.repository.HousingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideHousingDatabase(
        @ApplicationContext context: Context
    ): HousingDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            HousingDatabase::class.java,
            "housing_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideHousingRepository(
        database: HousingDatabase
    ): HousingRepository {
        return HousingRepository(database)
    }
}

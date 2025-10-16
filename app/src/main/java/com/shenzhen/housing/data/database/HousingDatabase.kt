package com.shenzhen.housing.data.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import android.content.Context
import com.shenzhen.housing.data.model.HousingPolicy
import com.shenzhen.housing.data.model.HousingProject
import com.shenzhen.housing.data.model.ApplicationGuide

@Database(
    entities = [HousingPolicy::class, HousingProject::class, ApplicationGuide::class],
    version = 1,
    exportSchema = false
)
abstract class HousingDatabase : RoomDatabase() {
    abstract fun housingPolicyDao(): HousingPolicyDao
    abstract fun housingProjectDao(): HousingProjectDao
    abstract fun applicationGuideDao(): ApplicationGuideDao

    companion object {
        @Volatile
        private var INSTANCE: HousingDatabase? = null

        fun getDatabase(context: Context): HousingDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HousingDatabase::class.java,
                    "housing_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

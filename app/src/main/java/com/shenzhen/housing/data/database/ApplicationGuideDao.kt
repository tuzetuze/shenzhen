package com.shenzhen.housing.data.database

import androidx.room.*
import com.shenzhen.housing.data.model.ApplicationGuide
import kotlinx.coroutines.flow.Flow

@Dao
interface ApplicationGuideDao {
    @Query("SELECT * FROM application_guides ORDER BY title ASC")
    fun getAllGuides(): Flow<List<ApplicationGuide>>

    @Query("SELECT * FROM application_guides WHERE type = :type ORDER BY title ASC")
    fun getGuidesByType(type: String): Flow<List<ApplicationGuide>>

    @Query("SELECT * FROM application_guides WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' ORDER BY title ASC")
    fun searchGuides(query: String): Flow<List<ApplicationGuide>>

    @Query("SELECT * FROM application_guides WHERE id = :id")
    suspend fun getGuideById(id: String): ApplicationGuide?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuide(guide: ApplicationGuide)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuides(guides: List<ApplicationGuide>)

    @Update
    suspend fun updateGuide(guide: ApplicationGuide)

    @Delete
    suspend fun deleteGuide(guide: ApplicationGuide)

    @Query("DELETE FROM application_guides")
    suspend fun deleteAllGuides()
}

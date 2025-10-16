package com.shenzhen.housing.data.database

import androidx.room.*
import com.shenzhen.housing.data.model.HousingProject
import kotlinx.coroutines.flow.Flow

@Dao
interface HousingProjectDao {
    @Query("SELECT * FROM housing_projects ORDER BY completionDate DESC")
    fun getAllProjects(): Flow<List<HousingProject>>

    @Query("SELECT * FROM housing_projects WHERE type = :type ORDER BY completionDate DESC")
    fun getProjectsByType(type: String): Flow<List<HousingProject>>

    @Query("SELECT * FROM housing_projects WHERE district = :district ORDER BY completionDate DESC")
    fun getProjectsByDistrict(district: String): Flow<List<HousingProject>>

    @Query("SELECT * FROM housing_projects WHERE status = :status ORDER BY completionDate DESC")
    fun getProjectsByStatus(status: String): Flow<List<HousingProject>>

    @Query("SELECT * FROM housing_projects WHERE name LIKE '%' || :query || '%' OR description LIKE '%' || :query || '%' ORDER BY completionDate DESC")
    fun searchProjects(query: String): Flow<List<HousingProject>>

    @Query("SELECT * FROM housing_projects WHERE id = :id")
    suspend fun getProjectById(id: String): HousingProject?

    @Query("SELECT * FROM housing_projects WHERE latitude BETWEEN :minLat AND :maxLat AND longitude BETWEEN :minLng AND :maxLng")
    fun getProjectsInArea(minLat: Double, maxLat: Double, minLng: Double, maxLng: Double): Flow<List<HousingProject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProject(project: HousingProject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProjects(projects: List<HousingProject>)

    @Update
    suspend fun updateProject(project: HousingProject)

    @Delete
    suspend fun deleteProject(project: HousingProject)

    @Query("DELETE FROM housing_projects")
    suspend fun deleteAllProjects()
}

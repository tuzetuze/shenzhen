package com.shenzhen.housing.data.database

import androidx.room.*
import com.shenzhen.housing.data.model.HousingPolicy
import kotlinx.coroutines.flow.Flow

@Dao
interface HousingPolicyDao {
    @Query("SELECT * FROM housing_policies WHERE isActive = 1 ORDER BY publishDate DESC")
    fun getAllPolicies(): Flow<List<HousingPolicy>>

    @Query("SELECT * FROM housing_policies WHERE category = :category AND isActive = 1 ORDER BY publishDate DESC")
    fun getPoliciesByCategory(category: String): Flow<List<HousingPolicy>>

    @Query("SELECT * FROM housing_policies WHERE title LIKE '%' || :query || '%' OR content LIKE '%' || :query || '%' AND isActive = 1 ORDER BY publishDate DESC")
    fun searchPolicies(query: String): Flow<List<HousingPolicy>>

    @Query("SELECT * FROM housing_policies WHERE id = :id")
    suspend fun getPolicyById(id: String): HousingPolicy?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPolicy(policy: HousingPolicy)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPolicies(policies: List<HousingPolicy>)

    @Update
    suspend fun updatePolicy(policy: HousingPolicy)

    @Delete
    suspend fun deletePolicy(policy: HousingPolicy)

    @Query("DELETE FROM housing_policies")
    suspend fun deleteAllPolicies()
}

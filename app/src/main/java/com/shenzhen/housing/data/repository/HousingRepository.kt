package com.shenzhen.housing.data.repository

import com.shenzhen.housing.data.database.HousingDatabase
import com.shenzhen.housing.data.model.HousingPolicy
import com.shenzhen.housing.data.model.HousingProject
import com.shenzhen.housing.data.model.ApplicationGuide
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HousingRepository @Inject constructor(
    private val database: HousingDatabase
) {
    // 政策相关
    fun getAllPolicies(): Flow<List<HousingPolicy>> = database.housingPolicyDao().getAllPolicies()
    
    fun getPoliciesByCategory(category: String): Flow<List<HousingPolicy>> = 
        database.housingPolicyDao().getPoliciesByCategory(category)
    
    fun searchPolicies(query: String): Flow<List<HousingPolicy>> = 
        database.housingPolicyDao().searchPolicies(query)
    
    suspend fun getPolicyById(id: String): HousingPolicy? = 
        database.housingPolicyDao().getPolicyById(id)
    
    suspend fun insertPolicy(policy: HousingPolicy) = 
        database.housingPolicyDao().insertPolicy(policy)
    
    suspend fun insertPolicies(policies: List<HousingPolicy>) = 
        database.housingPolicyDao().insertPolicies(policies)

    // 项目相关
    fun getAllProjects(): Flow<List<HousingProject>> = database.housingProjectDao().getAllProjects()
    
    fun getProjectsByType(type: String): Flow<List<HousingProject>> = 
        database.housingProjectDao().getProjectsByType(type)
    
    fun getProjectsByDistrict(district: String): Flow<List<HousingProject>> = 
        database.housingProjectDao().getProjectsByDistrict(district)
    
    fun getProjectsByStatus(status: String): Flow<List<HousingProject>> = 
        database.housingProjectDao().getProjectsByStatus(status)
    
    fun searchProjects(query: String): Flow<List<HousingProject>> = 
        database.housingProjectDao().searchProjects(query)
    
    fun getProjectsInArea(minLat: Double, maxLat: Double, minLng: Double, maxLng: Double): Flow<List<HousingProject>> = 
        database.housingProjectDao().getProjectsInArea(minLat, maxLat, minLng, maxLng)
    
    suspend fun getProjectById(id: String): HousingProject? = 
        database.housingProjectDao().getProjectById(id)
    
    suspend fun insertProject(project: HousingProject) = 
        database.housingProjectDao().insertProject(project)
    
    suspend fun insertProjects(projects: List<HousingProject>) = 
        database.housingProjectDao().insertProjects(projects)

    // 申请指南相关
    fun getAllGuides(): Flow<List<ApplicationGuide>> = database.applicationGuideDao().getAllGuides()
    
    fun getGuidesByType(type: String): Flow<List<ApplicationGuide>> = 
        database.applicationGuideDao().getGuidesByType(type)
    
    fun searchGuides(query: String): Flow<List<ApplicationGuide>> = 
        database.applicationGuideDao().searchGuides(query)
    
    suspend fun getGuideById(id: String): ApplicationGuide? = 
        database.applicationGuideDao().getGuideById(id)
    
    suspend fun insertGuide(guide: ApplicationGuide) = 
        database.applicationGuideDao().insertGuide(guide)
    
    suspend fun insertGuides(guides: List<ApplicationGuide>) = 
        database.applicationGuideDao().insertGuides(guides)
}

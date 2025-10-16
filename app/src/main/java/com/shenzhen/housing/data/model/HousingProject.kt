package com.shenzhen.housing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "housing_projects")
data class HousingProject(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val type: String, // 保障性住房、商品房、租赁住房等
    val location: String,
    val district: String,
    val latitude: Double,
    val longitude: Double,
    val totalUnits: Int,
    val availableUnits: Int,
    val priceRange: String,
    val areaRange: String,
    val developer: String,
    val completionDate: String,
    val status: String, // 规划中、建设中、已完工、已入住
    val images: List<String> = emptyList(),
    val facilities: List<String> = emptyList(),
    val transportation: List<String> = emptyList()
)

package com.shenzhen.housing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "housing_policies")
data class HousingPolicy(
    @PrimaryKey
    val id: String,
    val title: String,
    val content: String,
    val category: String,
    val publishDate: String,
    val effectiveDate: String,
    val department: String,
    val tags: List<String> = emptyList(),
    val isActive: Boolean = true
)

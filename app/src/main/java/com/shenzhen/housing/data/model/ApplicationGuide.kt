package com.shenzhen.housing.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "application_guides")
data class ApplicationGuide(
    @PrimaryKey
    val id: String,
    val title: String,
    val type: String, // 申请条件、申请流程、所需材料、时间安排
    val content: String,
    val steps: List<ApplicationStep> = emptyList(),
    val requiredDocuments: List<String> = emptyList(),
    val eligibilityCriteria: List<String> = emptyList(),
    val timeline: List<TimelineItem> = emptyList()
)

data class ApplicationStep(
    val stepNumber: Int,
    val title: String,
    val description: String,
    val estimatedTime: String
)

data class TimelineItem(
    val phase: String,
    val startDate: String,
    val endDate: String,
    val description: String
)

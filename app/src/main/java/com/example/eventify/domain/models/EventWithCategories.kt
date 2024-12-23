package com.example.eventify.domain.models

import androidx.compose.runtime.Stable
import com.example.eventify.data.models.CategoryInfo

@Stable
data class EventWithCategories(
    val id: String,
    val createdAt: Long,
    val modifiedAt: Long,
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Int,
    val moderated: Boolean,
    val ownerID: String,
    val start: Int,
    val state: String,
    val title: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<CategoryInfo>
)

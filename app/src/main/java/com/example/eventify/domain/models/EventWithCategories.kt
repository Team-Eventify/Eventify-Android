package com.example.eventify.domain.models

import androidx.compose.runtime.Stable

@Stable
data class EventWithCategories(
    val id: String,
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Int,
    val organizationID: String,
    val start: Int,
    val state: String,
    val title: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<Category>,
    val pictures: List<String>,
)

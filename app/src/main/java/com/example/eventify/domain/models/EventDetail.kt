package com.example.eventify.domain.models

data class EventDetail(
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Int,
    val id: String,
    val location: String,
    val organizationID: String,
    val pictures: List<String>,
    val categories: List<String>? = null,
    val start: Int,
    val state: String,
    val subscribed: Boolean,
    val title: String
)

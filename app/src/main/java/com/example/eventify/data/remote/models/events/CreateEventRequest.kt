package com.example.eventify.data.remote.models.events

data class CreateEventRequest(
    val description: String,
    val end: Int,
    val ownerID: String,
    val start: Int,
    val title: String
)
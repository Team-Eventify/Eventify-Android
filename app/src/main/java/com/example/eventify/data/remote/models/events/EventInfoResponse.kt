package com.example.eventify.data.remote.models.events

data class EventInfoResponse(
    val id: String,
    val CreatedAt: Long,
    val ModifiedAt: Long,
    val capacity: Int,
    val description: String,
    val end: Int,
    val moderated: Boolean,
    val ownerID: String,
    val start: Int,
    val state: String,
    val title: String
)
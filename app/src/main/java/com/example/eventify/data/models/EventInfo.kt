package com.example.eventify.data.models

data class EventInfo(
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
    val location: String
)

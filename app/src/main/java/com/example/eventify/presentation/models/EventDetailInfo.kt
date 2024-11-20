package com.example.eventify.presentation.models

data class EventDetailInfo(
    val id: String,
    val createdAt: Long,
    val modifiedAt: Long,
    val capacity: Int,
    val description: String,
    val end: Int,
    val moderated: Boolean,
    val ownerID: String,
    val start: Int,
    val state: String,
    val title: String
)

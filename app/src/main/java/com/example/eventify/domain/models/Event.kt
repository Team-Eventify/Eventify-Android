package com.example.eventify.domain.models

import com.example.eventify.presentation.models.ShortEventItem

data class Event(
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
    val categories: List<String>? = null
)


fun Event.toShortEventItem() = ShortEventItem(
    id = id,
    title = title,
    description = description,
    start = start,
    end = end,
    cover = cover
)
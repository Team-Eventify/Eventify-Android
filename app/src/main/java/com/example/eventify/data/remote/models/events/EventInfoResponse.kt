package com.example.eventify.data.remote.models.events

import com.example.eventify.data.models.EventInfo

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
    val title: String,
    val cover: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<String>? = null
)

fun EventInfoResponse.toEventInfo(): EventInfo = EventInfo(
    id = id,
    createdAt = CreatedAt,
    modifiedAt = ModifiedAt,
    capacity = capacity,
    description = description,
    end = end,
    moderated = moderated,
    ownerID = ownerID,
    start = start,
    state = state,
    title = title,
    location = location,
    cover = cover,
    subscribed = subscribed,
    categories = categories
)
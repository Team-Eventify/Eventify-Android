package com.example.eventify.data.remote.models.events

import com.example.eventify.domain.models.Event
import com.example.eventify.domain.models.toEventState

data class EventInfoResponse(
    val id: String,
    val capacity: Int,
    val description: String,
    val end: Long,
    val start: Long,
    val state: String,
    val title: String,
    val cover: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<String>? = null,
    val organizationID: String,
    val pictures: List<String>,
)

fun EventInfoResponse.toEventInfo(): Event = Event(
    id = id,
    capacity = capacity,
    description = description,
    end = end,
    organizationID = organizationID,
    start = start,
    state = state.toEventState(),
    title = title,
    location = location,
    cover = cover,
    subscribed = subscribed,
    categories = categories,
)
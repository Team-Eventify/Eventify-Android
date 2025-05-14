package data.models

import data.remote.models.events.EventInfoResponse


data class Event(
    val id: String,
    val capacity: Int,
    val cover: String,
    val description: String,
    val end: Long,
    val organizationID: String,
    val start: Long,
    val state: EventState,
    val title: String,
    val location: String,
    val subscribed: Boolean,
    val categories: List<String>? = null
)

fun EventInfoResponse.toBusiness(): Event = Event(
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




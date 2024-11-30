package com.example.eventify.data.remote.models.events

import java.security.Timestamp

data class EventsFilterData(
    val limit: Int? = null,
    val offset: Int? = null,
    val ownerId: String? = null,
    val start: Int? = null,
    val end: Int? = null,
)

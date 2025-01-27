package com.example.eventify.domain.models

data class FullEventDetail(
    val eventInfo: EventDetail,
    val categories: List<Category>,
    val organization: Organization,
)

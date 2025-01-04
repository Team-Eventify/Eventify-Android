package com.example.eventify.presentation.ui.events.eventdetail

import com.example.eventify.domain.models.EventWithCategories


/**
 * UI State that represents EventDetailScreen
 **/
data class EventDetailState(
    val event: EventWithCategories? = null
)

/**
 * EventDetail Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class EventDetailActions(
    val navigateUp: () -> Unit = {},
    val onSubscribe: () -> Unit = {},
    val onUnsubscribe: () -> Unit = {},
    val goToRatePage: () -> Unit = {},
)
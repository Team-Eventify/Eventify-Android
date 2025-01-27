package com.example.eventify.presentation.ui.events.eventdetail

import com.example.eventify.domain.models.FullEventDetail


/**
 * UI State that represents EventDetailScreen
 **/
sealed class UiState {
    data object Loading : UiState()
    data class Error(
        val message: String,
    ) : UiState()
    data class ShowEvent(
        val event: FullEventDetail,
    ) : UiState()
}

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
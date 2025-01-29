package com.example.eventify.presentation.ui.events.myevents

import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents MyEventsScreen
 **/
sealed class UiState {
    data object Initial : UiState()
    data class Empty(
        val isRefreshing: Boolean = false,
    ) : UiState()
    data class Error(
        val isRefreshing: Boolean = false,
        val message: String? = null,
    ) : UiState()
    data class ShowMyEvents(
        val upComingEvents: List<ShortEventItem> = emptyList(),
        val finishedEvents: List<ShortEventItem> = emptyList(),
        val isRefreshing: Boolean = false,
    ) : UiState()
}

/**
 * MyEvents Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MyEventsActions(
    val onRefresh: () -> Unit = {},
    val navigateToEvent: (String) -> Unit = {},
    val navigateToFeedback: (String) -> Unit = {},
    val navigateToFeed: () -> Unit = {},
)
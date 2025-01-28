package com.example.eventify.presentation.ui.events.myevents

import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents MyEventsScreen
 **/
sealed class UiState(
//    open val isRefreshing: Boolean = false,
) {
    data object Initial : UiState()
    data object Empty : UiState()
    data object Error : UiState()
    data class ShowMyEvents(
        val upComingEvents: List<ShortEventItem> = emptyList(),
        val finishedEvents: List<ShortEventItem> = emptyList(),
    ) : UiState()
}

/**
 * MyEvents Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MyEventsActions(
    val onRefresh: () -> Unit = {},
    val navigateToEvent: (String) -> Unit = {},
    val navigateToFeedback: (String) -> Unit = {}
)
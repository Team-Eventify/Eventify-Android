package com.example.eventify.presentation.ui.events.myevents

import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents MyEventsScreen
 **/
data class MyEventsState(
    val isRefreshing: Boolean = false,
    val isLoading: Boolean = false,
    val upComingEvents: List<ShortEventItem> = emptyList(),
    val finishedEvents: List<ShortEventItem> = emptyList(),
)

/**
 * MyEvents Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class MyEventsActions(
    val onRefresh: () -> Unit,
    val navigateToEvent: (String) -> Unit
)
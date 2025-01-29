package com.example.eventify.presentation.ui.events.eventsfeed

import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents EventsFeedScreen
 **/

sealed class UiState {
    data class Error(
        val message: String? = null,
        val isRefreshing: Boolean = false,
    ) : UiState()
    data object Loading : UiState()
    data class ShowFeed(
        val events: List<ShortEventItem>,
        val isRefreshing: Boolean = false,
    ) : UiState()
}

/**
 * EventsFeed Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class EventsFeedActions(
    val onEventClick: (String) -> Unit,
    val onLoadData: () -> Unit,
    val onRefreshData: () -> Unit
) {
    companion object {
        fun default() = EventsFeedActions(
            onEventClick = {},
            onLoadData = {},
            onRefreshData = {},
        )
    }
}
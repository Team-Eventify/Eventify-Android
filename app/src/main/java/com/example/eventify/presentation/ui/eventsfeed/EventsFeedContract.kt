package com.example.eventify.presentation.ui.eventsfeed

import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents EventsFeedScreen
 **/
data class EventsFeedState(
    val isRefreshing: Boolean = false,
    val events: List<ShortEventItem>,
){
    companion object {
        fun default() = EventsFeedState(
            events = emptyList(),
        )
    }
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
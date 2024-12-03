package com.example.eventify.presentation.ui.eventsfeed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.data.models.CategoryInfo
import com.example.eventify.presentation.models.EventFeedResult
import com.example.eventify.presentation.models.ShortEventItem


/**
 * UI State that represents EventsFeedScreen
 **/
data class EventsFeedState(
    val events: List<ShortEventItem>,
    val categories: List<CategoryInfo>,
    val result: EventFeedResult
){
    companion object {
        fun default() = EventsFeedState(
            events = emptyList(),
            categories = emptyList(),
            result = EventFeedResult.Idle
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
package com.example.eventify.presentation.ui.events.eventsfeed.state

import com.example.eventify.presentation.models.ShortEventItem

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
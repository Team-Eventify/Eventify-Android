package com.example.eventify.presentation.models

sealed class EventFeedResult {
    object Idle : EventFeedResult()
    object Loading : EventFeedResult()
    object Success : EventFeedResult()
    object Refreshing : EventFeedResult()
    data class Error(val message: String): EventFeedResult()
}

data class EventFeedUiState(
    val isRefreshing: Boolean = false
) {
    companion object {
        fun default() = EventFeedUiState()
    }
}

//data class EventFeedUiState(
//
//)
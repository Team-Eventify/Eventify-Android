package com.example.eventify.presentation.ui.events.eventdetail.state

import com.example.eventify.domain.models.FullEventDetail

sealed class EventDetailUiState {
    data object Loading : EventDetailUiState()
    data class Error(
        val message: String,
    ) : EventDetailUiState()
    data class ShowEvent(
        val event: FullEventDetail,
    ) : EventDetailUiState()
}
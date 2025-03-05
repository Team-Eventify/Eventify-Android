package com.example.eventify.presentation.ui.events.eventdetail.state

import com.example.eventify.domain.models.FullEventDetail

sealed class UiState {
    data object Loading : UiState()
    data class Error(
        val message: String,
    ) : UiState()
    data class ShowEvent(
        val event: FullEventDetail,
    ) : UiState()
}
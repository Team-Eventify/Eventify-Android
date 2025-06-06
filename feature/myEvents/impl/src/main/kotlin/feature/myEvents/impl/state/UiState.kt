package feature.myEvents.impl.state

import domain.models.ShortEventItem

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

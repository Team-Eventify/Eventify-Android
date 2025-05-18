package feature.eventDetail.impl.state

import domain.models.FullEventDetail


internal sealed class EventDetailUiState {
    data object Loading : EventDetailUiState()
    data object Error : EventDetailUiState()
    data class ShowEvent(
        val isRefreshing: Boolean = false,
        val event: FullEventDetail,
    ) : EventDetailUiState()
}
package feature.eventDetail.impl.state

import domain.models.FullEventDetail


internal sealed class EventDetailUiState {
    data object Loading : EventDetailUiState()
    data class Error(
        val message: String,
    ) : EventDetailUiState()
    data class ShowEvent(
        val event: FullEventDetail,
    ) : EventDetailUiState()
}
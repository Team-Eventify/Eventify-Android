package feature.eventDetail.impl.state


internal sealed class EventDetailUiState {
    data object Loading : EventDetailUiState()
    data class Error(
        val message: String,
    ) : EventDetailUiState()
    data class ShowEvent(
        val event: FullEventDetail,
    ) : EventDetailUiState()
}
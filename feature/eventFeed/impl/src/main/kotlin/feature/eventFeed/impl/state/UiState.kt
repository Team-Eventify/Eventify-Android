package feature.eventFeed.impl.state


internal sealed class UiState {
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
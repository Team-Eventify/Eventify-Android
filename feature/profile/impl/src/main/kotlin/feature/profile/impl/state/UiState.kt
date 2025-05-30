package feature.profile.impl.state

import domain.models.UserShortInfo


sealed class UiState {
    data class ShowProfile(
        val userInfo: UserShortInfo,
    ) : UiState()
    data object Loading : UiState()
    data object Error : UiState()
}
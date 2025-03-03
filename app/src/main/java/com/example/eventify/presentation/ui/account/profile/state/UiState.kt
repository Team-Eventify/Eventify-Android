package com.example.eventify.presentation.ui.account.profile.state

import com.example.eventify.presentation.models.UserShortInfo

sealed class UiState {
    data class ShowProfile(
        val userInfo: UserShortInfo,
    ) : UiState()
    data object Loading : UiState()
    data object Error : UiState()
}
package com.example.eventify.presentation.ui.account.profile_decor.state

import androidx.compose.runtime.Stable

@Stable
sealed class ProfileDecorUiState {
    data class ProfileDecorData(
        val typeOfTheme: Int? = null
    ): ProfileDecorUiState()

    data object Error : ProfileDecorUiState()
    data object Loading : ProfileDecorUiState()
}
package com.example.eventify.presentation.ui.account.profileedit.state

import androidx.compose.runtime.Stable
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.utils.UiText

@Stable
sealed class UiState {
    data class ShowProfileEdit(
        val email: String = "",
        val hasEmailError: Boolean = false,
        val emailError: UiText? = null,

        val firstName: String = "",
        val firstNameError: String? = null,

        val lastName: String = "",
        val lastNameError: String? = null,

        val telegramName: String = "",
        val hasTelegramNameError: Boolean = false,
        val telegramNameError: UiText? = null,

        val categoryItems: List<CategorySelectItem> = emptyList()
    ) : UiState()

    data object Error : UiState()
    data object Loading : UiState()
}
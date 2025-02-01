package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Stable
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.presentation.models.CategorySelectItem
import com.example.eventify.presentation.utils.UiText


/**
 * UI State that represents ProfileEditScreen
 **/

@Stable
data class ProfileEditState(
    val error: String? = null,
    val isLoading: Boolean = false,

    val email: String = "",
    val hasEmailError: Boolean = false,
    val emailError: UiText? = null,

    val firstName: String = "",
    val firstNameError: String? = null,

    val lastName: String = "",
    val lastNameError: String? = null,

    val middleName: String = "",
    val middleNameError: String? = null,

    val telegramName: String = "",
    val hasTelegramNameError: Boolean = false,
    val telegramNameError: UiText? = null,

    val categoryItems: List<CategorySelectItem> = emptyList()
)

/**
 * ProfileEdit Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfileEditActions(
    val onSubmit: () -> Unit = {},
    val onChangeCategoryFilterActive: (String, Boolean) -> Unit = {_, _ ->},
    val onChangeEmail: (String) -> Unit = {},
    val onChangeFirstName: (String) -> Unit = {},
    val onChangeLastName: (String) -> Unit = {},
    val onChangeMiddleName: (String) -> Unit = {},
    val onChangeTelegram: (String) -> Unit = {},
    val onDeleteAccount: () -> Unit = {},
)

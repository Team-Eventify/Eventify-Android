package com.example.eventify.presentation.ui.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.eventify.presentation.models.CategorySelectItem


/**
 * UI State that represents ProfileEditScreen
 **/


data class ProfileEditState(
    val error: String? = null,
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,

    val email: String,
    val emailError: String? = null,

    val firstName: String,
    val firstNameError: String? = null,

    val lastName: String,
    val lastNameError: String? = null,

    val middleName: String,
    val middleNameError: String? = null,

    val telegramName: String,
    val telegramNameError: String? = null,

    val categoryItems: List<CategorySelectItem>
){
    companion object{
        fun default() = ProfileEditState(
            email = "",
            firstName = "",
            lastName = "",
            middleName = "",
            telegramName = "",
            categoryItems = emptyList()
        )
    }
}
/**
 * ProfileEdit Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/
data class ProfileEditActions(
    val onSubmit: () -> Unit,
    val onToggleCategoryItem: (String) -> Unit,
    val onChangeEmail: (String) -> Unit,
    val onChangeFirstName: (String) -> Unit,
    val onChangeLastName: (String) -> Unit,
    val onChangeMiddleName: (String) -> Unit,
    val onChangeTelegram: (String) -> Unit,
)
{
    companion object{
        fun default() = ProfileEditActions(
            onSubmit = {},
            onToggleCategoryItem = {},
            onChangeEmail = {},
            onChangeFirstName = {},
            onChangeLastName = {},
            onChangeMiddleName = {},
            onChangeTelegram = {},
        )
    }
}
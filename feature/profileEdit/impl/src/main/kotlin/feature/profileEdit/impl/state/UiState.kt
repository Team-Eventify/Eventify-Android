package feature.profileEdit.impl.state

import androidx.compose.runtime.Stable
import domain.models.CategorySelectItem

@Stable
sealed class UiState {
    data class ShowProfileEdit(
        val email: String = "",
        val hasEmailError: Boolean = false,
        val emailError: String? = null,

        val firstName: String = "",
        val firstNameError: String? = null,

        val lastName: String = "",
        val lastNameError: String? = null,

        val telegramName: String = "",
        val hasTelegramNameError: Boolean = false,
        val telegramNameError: String? = null,

        val categoryItems: List<CategorySelectItem> = emptyList(),
        val categoriesError: String? = null
    ) : UiState()

    data object Error : UiState()
    data object Loading : UiState()
}
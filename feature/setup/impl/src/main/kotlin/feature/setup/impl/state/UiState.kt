package feature.setup.impl.state

import domain.models.CategorySelectItem


data class MainSetupUiState(
    val currentStep: SetupStep = SetupStep.Initial,
    val categoriesState: ChooseCategoriesState = ChooseCategoriesState(),
    val userDataState: UserDataState = UserDataState(),
)


sealed class SetupStep {
    data object Initial : SetupStep()
    data object ChooseCategories : SetupStep()
    data object SetUserData : SetupStep()
}

data class ChooseCategoriesState(
    val categories: List<CategorySelectItem> = emptyList(),
    val error: String? = null,
)

data class UserDataState(
    val firstName: String = "",
    val lastName: String = "",
)
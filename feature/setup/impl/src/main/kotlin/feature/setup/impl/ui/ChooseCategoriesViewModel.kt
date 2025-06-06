package feature.setup.impl.ui

import android.content.Context
import androidx.lifecycle.viewModelScope
import core.common.BaseViewModel
import core.common.extentions.toColorOrNull
import feature.setup.impl.state.ChooseCategoriesState
import feature.setup.impl.state.MainSetupUiState
import feature.setup.impl.state.SetupStep
import feature.setup.impl.state.SideEffect
import feature.setup.impl.state.UserDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import data.models.Category
import data.models.UserChange
import domain.account.ChangeUserUseCase
import domain.account.SetUserCategoriesUseCase
import domain.categories.GetCategoriesUseCase
import domain.models.CategorySelectItem
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import com.example.eventify.core.common.R as CommonR


@HiltViewModel
class ChooseCategoriesViewModel @Inject constructor(
    private val setCategoriesUseCase: SetUserCategoriesUseCase,
    private val changeUserUseCase: ChangeUserUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    @ApplicationContext private val context: Context,
) : BaseViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val mutableCategoriesState = MutableStateFlow(ChooseCategoriesState())
    private val mutableUserDataState = MutableStateFlow(UserDataState())
    private val mutableSetupStep = MutableStateFlow<SetupStep>(SetupStep.ChooseCategories)

    val stateFlow = combine(
        mutableCategoriesState,
        mutableUserDataState,
        mutableSetupStep,
    ) { categories, userData, step ->
        MainSetupUiState(
            currentStep = step,
            userDataState = userData,
            categoriesState = categories,
        )
    }
        .onStart {
            fetchCategoriesData()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            MainSetupUiState()
        )

    private fun fetchCategoriesData() {
        launchCatching(
            catch = {
                mutableSideEffect.trySend(SideEffect.FailedProvideCategories(it.message))
            }
        ) {
            mutableCategoriesState.update {
                ChooseCategoriesState(
                    categories = getCategoriesUseCase().map { it.toSelectedItem() }
                )
            }
        }
    }


    fun flowNextStep() {
        when (mutableSetupStep.value) {
            SetupStep.Initial -> {}
            SetupStep.SetUserData -> {

                mutableSetupStep.update { SetupStep.ChooseCategories }
            }
            SetupStep.ChooseCategories -> {
                setCategoriesAndFlowNext()
            }
        }
    }

    fun flowPreviousStep() {
        when (mutableSetupStep.value) {
            SetupStep.Initial -> {}
            SetupStep.ChooseCategories -> {
                mutableSetupStep.update { SetupStep.SetUserData }
            }
            SetupStep.SetUserData -> {}
        }
    }

    fun changeFirstName(value: String) {
        mutableUserDataState.update { currentState ->
            currentState.copy(
                firstName = value
            )
        }
    }

    fun changeLastName(value: String) {
        mutableUserDataState.update { currentState ->
            currentState.copy(
                lastName = value
            )
        }
    }

    fun changeCategoryFilterActive(categoryId: String, value: Boolean) {
        mutableCategoriesState.update { currentState ->
            currentState.copy(
                error = null,
                categories = currentState.categories.map { category ->
                    category.copy(selected = value).takeIf { it.id == categoryId } ?: category
                }
            )
        }
    }

    private fun setCategoriesAndFlowNext() {
        launchCatching(
            catch = {
                mutableSideEffect.trySend(SideEffect.FailedProvideCategories(it.message))
            }
        ) {
            val categoriesId = mutableCategoriesState.value.categories
                .filter { it.selected }
                .map { it.id }

            if (categoriesId.isEmpty()) {
                mutableCategoriesState.update { currentState ->
                    currentState.copy(
                        error = context.getString(CommonR.string.needs_select_minimum_one_category)
                    )
                }
                return@launchCatching
            }

            setCategoriesUseCase(categoriesId)
            mutableSideEffect.trySend(SideEffect.FinishSetUp)
        }
    }

    private fun setUserDataAndFlowNext() {
        // TODO доделать
        launchCatching(
            catch = {
                mutableSideEffect.trySend(SideEffect.FailedSetUserData)
            }
        ) {
            val userData = mutableUserDataState.value.let {
                UserChange(
                    email = "",
                    firstName = it.firstName,
                    lastName = it.lastName,
                    telegramName = ""
                )
            }
            changeUserUseCase(userData)
        }
    }

    private fun Category.toSelectedItem() = CategorySelectItem(
        id = this.id,
        title = this.title,
        selected = false,
        isShow = true,
        color = this.color.toColorOrNull()!!,
    )
}
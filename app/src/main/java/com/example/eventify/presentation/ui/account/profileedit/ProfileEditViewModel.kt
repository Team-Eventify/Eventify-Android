package com.example.eventify.presentation.ui.account.profileedit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.domain.models.UserChange
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.GetCategoriesWithUserSelection
import com.example.eventify.domain.usecases.account.ChangeUserUseCase
import com.example.eventify.domain.usecases.account.DeleteAccountUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidateTelegramName
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.ui.account.profileedit.state.UiState
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCategoriesWithUserSelection: GetCategoriesWithUserSelection,
    private val changeUserUseCase: ChangeUserUseCase,
    private val setUserCategoriesUseCase: SetUserCategoriesUseCase,
    private val navigator: Navigator,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val validateTelegramNameUseCase = ValidateTelegramName()
    private val validateEmailUseCase = ValidateEmail()

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )



    private fun loadData(){
        viewModelScope.launch {
            when (val userResult = getCurrentUserUseCase()){
                is Result.Error -> SnackbarController.sendEvent(
                    SnackbarEvent(message = userResult.error.asUiText().asString(context))
                )
                is Result.Success -> {
                    val user = userResult.data
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            firstName = user.firstName,
                            lastName = user.lastName,
                            email = user.email,
                            telegramName = user.telegramName
                        )
                    }
                }
            }

            when (val categoriesResult = getCategoriesWithUserSelection()){
                is Result.Error -> SnackbarController.sendEvent(
                    SnackbarEvent(message = categoriesResult.error.asUiText().asString(context))
                )
                is Result.Success -> {
                    _stateFlow.update { currentState ->
                        currentState.copy(
                            categoryItems = categoriesResult.data
                        )
                    }
                }
            }
        }

    }


    fun saveUser(){
        val isValidaData = listOf(
            validateEmail(),
            validateTelegramName()
        ).all { it }

        if (!isValidaData) return

        val userData = stateFlow.value.run {
            UserChange(
                firstName = firstName,
                lastName = lastName,
                email = email,
                telegramName = telegramName
            )
        }
        val categoryIds = stateFlow.value.run {
            categoryItems.filter { categoryItem -> categoryItem.selected }.map { categoryItem -> categoryItem.id }
        }

        viewModelScope.launch {
            when (val userResult = changeUserUseCase(userData)){
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = userResult.error.asUiText().asString(context))
                    )
                    return@launch
                }
                is Result.Success -> {}
            }

            when (val categoriesResult = setUserCategoriesUseCase(categoryIds = categoryIds)){
                is Result.Error -> {
                    SnackbarController.sendEvent(
                        SnackbarEvent(message = categoriesResult.error.asUiText().asString(context))
                    )
                    return@launch
                }
                is Result.Success -> {}
            }

            SnackbarController.sendEvent(
                SnackbarEvent(message = context.getString(R.string.user_updated))
            )
        }
    }



    fun changeCategoryFilterActive(categoryId: String, value: Boolean) {
        _stateFlow.update { currentState ->
            currentState.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = value)
                    } else {
                        category
                    }
                }
            )
        }
    }


    fun navigateBack(){
        viewModelScope.launch {
            navigator.navigateUp()
        }
    }


    fun changeUserEmail(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                email = value
            )
        }
    }

    private fun validateEmail(): Boolean{
        val validationResult = validateEmailUseCase(stateFlow.value.email)
        val (error, hasError) = when (validationResult) {
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                emailError = error,
                hasEmailError = hasError
            )
        }
        return validationResult is Result.Success
    }

    fun changeUserFirstName(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                firstName = value
            )
        }
    }
    fun changeUserLastName(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                lastName = value
            )
        }
    }
    fun changeUserTelegram(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                telegramName = value
            )
        }
    }

    private fun validateTelegramName(): Boolean{
        val validationResult = validateTelegramNameUseCase(stateFlow.value.telegramName)
        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            currentState.copy(
                telegramNameError = error,
                hasTelegramNameError = hasError
            )
        }

        return validationResult is Result.Success
    }

    fun deleteAccount() {
        viewModelScope.launch {
            deleteAccountUseCase()
            navigator.navigate(RootRouter.AuthRoute){
                popUpTo(0) {
                    inclusive = true
                }
            }
        }
    }


}
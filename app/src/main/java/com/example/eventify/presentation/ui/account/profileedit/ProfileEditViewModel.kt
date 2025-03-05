package com.example.eventify.presentation.ui.account.profileedit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.domain.usecases.GetCategoriesWithUserSelection
import com.example.eventify.domain.usecases.account.ChangeUserUseCase
import com.example.eventify.domain.usecases.account.DeleteAccountUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidateTelegramName
import com.example.eventify.presentation.ui.account.profileedit.state.SideEffect
import com.example.eventify.presentation.ui.account.profileedit.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.eventify.domain.Result
import com.example.eventify.domain.models.UserChange
import com.example.eventify.presentation.utils.asText
import com.example.eventify.presentation.utils.asUiText

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCategoriesWithUserSelection: GetCategoriesWithUserSelection,
    private val changeUserUseCase: ChangeUserUseCase,
    private val setUserCategoriesUseCase: SetUserCategoriesUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val validateTelegramNameUseCase = ValidateTelegramName()
    private val validateEmailUseCase = ValidateEmail()

    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<UiState> = MutableStateFlow(UiState.Loading)
    val stateFlow: StateFlow<UiState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            UiState.Loading
        )


    private fun loadData() {
        viewModelScope.launch {
            _stateFlow.update { currentState ->
                val userData = when (val userResult = getCurrentUserUseCase()) {
                    is Result.Error -> return@update UiState.Error
                    is Result.Success -> userResult.data
                }
                val categories = when (val categoriesResult = getCategoriesWithUserSelection()) {
                    is Result.Error -> return@update UiState.Error
                    is Result.Success -> categoriesResult.data
                }

                UiState.ShowProfileEdit(
                    firstName = userData.firstName,
                    lastName = userData.lastName,
                    email = userData.email,
                    telegramName = userData.telegramName,
                    categoryItems = categories,
                )
            }
        }
    }


    fun saveUser() {
        val isValidaData = listOf(
            validateEmail(),
            validateTelegramName()
        ).all { it }

        if (!isValidaData) return

        val userData = (stateFlow.value as? UiState.ShowProfileEdit)?.let {
            UserChange(
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email,
                telegramName = it.telegramName
            )
        } ?: return

        val categoryIds = (stateFlow.value as? UiState.ShowProfileEdit)?.let {
            it.categoryItems.filter { categoryItem -> categoryItem.selected }.map { categoryItem -> categoryItem.id }
        } ?: return

        viewModelScope.launch {
            when (val userResult = changeUserUseCase(userData)){
                is Result.Error -> {
                    mutableSideEffect.send(SideEffect.FailUpdate(
                        userResult.asText(context)
                    ))
                    return@launch
                }
                is Result.Success -> {}
            }

            when (val categoriesResult = setUserCategoriesUseCase(categoryIds = categoryIds)){
                is Result.Error -> {
                    mutableSideEffect.send(SideEffect.FailUpdate(
                        categoriesResult.asText(context)
                    ))
                    return@launch
                }
                is Result.Success -> {}
            }

            mutableSideEffect.send(SideEffect.SuccessUpdate)
        }
    }


    fun changeCategoryFilterActive(categoryId: String, value: Boolean) {
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = value)
                    } else {
                        category
                    }
                }
            ) ?: currentState
        }
    }


    fun changeUserEmail(value: String) {
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                email = value
            ) ?: currentState
        }
    }


    private fun validateEmail(): Boolean{
        val validationResult = (_stateFlow.value as? UiState.ShowProfileEdit)?.let {
            validateEmailUseCase(it.email)
        } ?: return false

        val (error, hasError) = when (validationResult) {
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                emailError = error,
                hasEmailError = hasError
            ) ?: currentState
        }
        return validationResult is Result.Success
    }

        fun changeUserFirstName(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    firstName = value
                ) ?: currentState
            }
        }

        fun changeUserLastName(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    lastName = value
                ) ?: currentState
            }
        }

        fun changeUserTelegram(value: String) {
            _stateFlow.update { currentState ->
                (currentState as? UiState.ShowProfileEdit)?.copy(
                    telegramName = value
                ) ?: currentState
            }
        }

    private fun validateTelegramName(): Boolean{
        val validationResult = (_stateFlow.value as? UiState.ShowProfileEdit)?.let {
            validateTelegramNameUseCase(it.telegramName)
        } ?: return false

        val (error, hasError) = when (validationResult){
            is Result.Error -> validationResult.error.asUiText() to true
            is Result.Success -> null to false
        }
        _stateFlow.update { currentState ->
            (currentState as? UiState.ShowProfileEdit)?.copy(
                telegramNameError = error,
                hasTelegramNameError = hasError
            ) ?: currentState
        }

        return validationResult is Result.Success
    }

    fun deleteAccount() {
        viewModelScope.launch {
            deleteAccountUseCase()
            mutableSideEffect.send(SideEffect.DeleteAccount)
        }
    }
}

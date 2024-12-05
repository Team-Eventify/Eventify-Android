package com.example.eventify.presentation.ui.profileedit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.models.UserInfo
import com.example.eventify.domain.usecases.GetCategoriesWithUserSelection
import com.example.eventify.domain.usecases.account.ChangeUserUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidateTelegramName
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getCategoriesWithUserSelection: GetCategoriesWithUserSelection,
    private val changeUserUseCase: ChangeUserUseCase,
    private val setUserCategoriesUseCase: SetUserCategoriesUseCase
) : ViewModel() {
    private val validateTelegramName = ValidateTelegramName()
    private val validateEmail = ValidateEmail()

    private val _currentUser: MutableStateFlow<UserInfo?> = MutableStateFlow(null)
    val currentUser: StateFlow<UserInfo?> = _currentUser.asStateFlow()

    private val _stateFlow: MutableStateFlow<ProfileEditState> = MutableStateFlow(ProfileEditState.default())
    val stateFlow: StateFlow<ProfileEditState> = _stateFlow.asStateFlow()


    init {
        loadData()
    }

    private fun loadData(){
        // TODO refactor this block

        viewModelScope.launch {
            _currentUser.value = getCurrentUserUseCase()
            _stateFlow.update { currentState ->
                currentUser.value?.run {
                    currentState.copy(
                        firstName = firstName,
                        lastName = lastName,
                        middleName = middleName,
                        email = email,
                        telegramName = telegramName,
                        categoryItems = getCategoriesWithUserSelection(id)
                    )
                } ?: ProfileEditState.default()

            }
        }
    }


    fun saveUser(){
        if (validateForm()) return

        val userData = stateFlow.value.run {
            UserChange(
                firstName = firstName,
                lastName = lastName,
                middleName = middleName,
                email = email,
                telegramName = telegramName
            )
        }
        val categoryIds = stateFlow.value.run {
            categoryItems.filter { categoryItem -> categoryItem.selected }.map { categoryItem -> categoryItem.id }
        }

        viewModelScope.launch {
            runCatching {
                currentUser.value?.run {
                    changeUserUseCase(userId = id, data = userData)
                    setUserCategoriesUseCase(userId = id, categoryIds = categoryIds)
                }
            }.onSuccess {
                SnackbarController.sendEvent(
                    SnackbarEvent(message = "Пользователь обновлен")
                )
                loadData()
            }.onFailure {  exception ->
                // TODO show errors detail
                SnackbarController.sendEvent(
                    SnackbarEvent(message = exception.message ?: "Не удалось обновить данные")
                )
            }
        }
    }



    fun toggleCategorySelection(categoryId: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                categoryItems = currentState.categoryItems.map { category ->
                    if (category.id == categoryId) {
                        category.copy(selected = !category.selected)
                    } else {
                        category
                    }
                }
            )
        }
    }

    private fun validateForm(): Boolean {
        val isValidEmail = validateEmail(stateFlow.value.email)
        val isValidTelegramName = validateTelegramName(stateFlow.value.telegramName)

        val hasErrors = listOf(
            isValidEmail,
            isValidTelegramName
        ).any { !it.successful }

        _stateFlow.update { currentState ->
            currentState.copy(
                hasTelegramNameError = !isValidTelegramName.successful,
                telegramNameError = isValidTelegramName.errorMessage,
                hasEmailError = !isValidEmail.successful,
                emailError = isValidEmail.errorMessage
            )
        }

        return hasErrors
    }


    fun changeUserEmail(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                email = value
            )
        }
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
    fun changeUserMiddleName(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                middleName = value
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


}
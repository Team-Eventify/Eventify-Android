package com.example.eventify.presentation.ui.account.profileedit

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserCredentials
import com.example.eventify.data.models.UserInfo
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.GetCategoriesWithUserSelection
import com.example.eventify.domain.usecases.account.ChangeUserUseCase
import com.example.eventify.domain.usecases.account.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.account.SetUserCategoriesUseCase
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.domain.validation.ValidateTelegramName
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.SnackbarEvent
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
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
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val validateTelegramNameUseCase = ValidateTelegramName()
    private val validateEmailUseCase = ValidateEmail()

    private val _currentUser: MutableStateFlow<UserInfo?> = MutableStateFlow(null)
    val currentUser: StateFlow<UserInfo?> = _currentUser.asStateFlow()

    private val _stateFlow: MutableStateFlow<ProfileEditState> = MutableStateFlow(ProfileEditState.default())
    val stateFlow: StateFlow<ProfileEditState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            ProfileEditState.default()
        )



    private fun loadData(){
        // TODO refactor this block
//        viewModelScope.launch {
//            when (val result = getCurrentUserUseCase()) {
//                is Result.Error -> TODO()
//                is Result.Success -> {
//                    val user = result.data
//                    _currentUser.value = user
//                    _stateFlow.update { currentState ->
//                        currentState.copy(
//                            firstName = user.firstName,
//                            lastName = user.lastName,
//                            middleName = user.middleName,
//                            email = user.email,
//                            telegramName = user.telegramName,
//                        )
//                    }
//                }
//            }
//
//            when (val result = getCategoriesWithUserSelection(_currentUser.value!!.id)){
//                is Result.Error -> {
//                    when (result.error){
//                        is DataError.API -> {
//                            when (result.error){
//                                DataError.API.NOT_FOUND -> _stateFlow.update { currentState ->
//                                    currentState.copy(
//                                        categoryItems = emptyList()
//                                    )
//                                }
//                                else -> SnackbarController.sendEvent(
//                                    SnackbarEvent(message = result.error.asUiText().asString(context))
//                                )
//                            }
//                        }
//                        else -> SnackbarController.sendEvent(
//                            SnackbarEvent(message = result.error.asUiText().asString(context))
//                        )
//                    }
//                }
//                is Result.Success -> {
//                    _stateFlow.update { currentState ->
//                        currentState.copy(
//                            categoryItems = result.data
//                        )
//                    }
//                }
//            }
//        }
    }


    fun saveUser(){
//        val isValidaData = listOf(
//            validateEmail(),
//            validateTelegramName()
//        ).all { it }
//
//        if (!isValidaData) return
//
//        val userData = stateFlow.value.run {
//            UserChange(
//                firstName = firstName,
//                lastName = lastName,
//                middleName = middleName,
//                email = email,
//                telegramName = telegramName
//            )
//        }
//        val categoryIds = stateFlow.value.run {
//            categoryItems.filter { categoryItem -> categoryItem.selected }.map { categoryItem -> categoryItem.id }
//        }

//        viewModelScope.launch {
//            runCatching {
//                currentUser.value?.run {
//                    changeUserUseCase(userId = id, data = userData)
//                    setUserCategoriesUseCase(userId = id, categoryIds = categoryIds)
//                }
//            }.onSuccess {
//                SnackbarController.sendEvent(
//                    SnackbarEvent(message = "Пользователь обновлен")
//                )
//                loadData()
//            }.onFailure {  exception ->
//                // TODO show errors detail
//                SnackbarController.sendEvent(
//                    SnackbarEvent(message = exception.message ?: "Не удалось обновить данные")
//                )
//            }
//        }

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


}
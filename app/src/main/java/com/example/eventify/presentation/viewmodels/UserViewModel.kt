package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserChange
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.usecases.GetCurrentUserUseCase
import com.example.eventify.domain.usecases.LogOutUseCase
import com.example.eventify.presentation.models.UserResult
import com.example.eventify.presentation.models.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    val getCurrentUserUseCase: GetCurrentUserUseCase,
    val logOutUseCase: LogOutUseCase,
    val usersRepository: UsersRepository
): ViewModel() {

    var uiState by mutableStateOf(UserUiState.default())
        private set

    var changeUserResult by mutableStateOf<UserResult>(UserResult.Idle)
    var loadUserResult by mutableStateOf<UserResult>(UserResult.Idle)

    var user by mutableStateOf<UserInfo?>(null)
        private set


    fun changeFirstName(value: String){
        uiState = uiState.copy(
            firstName = value
        )
    }

    fun changeLastName(value: String){
        uiState = uiState.copy(
            lastName = value
        )
    }

    fun changeMiddleName(value: String){
        uiState = uiState.copy(
            middleName = value
        )
    }

    fun changeEmail(value: String){
        uiState = uiState.copy(
            email = value
        )
    }

    fun changeTelegram(value: String){
        uiState = uiState.copy(
            telegramName = value
        )
    }


    fun loadUserInfo(force: Boolean = false){
        if (user != null || force) return

        loadUserResult = UserResult.Loading
        viewModelScope.launch {
            try {
                user = getCurrentUserUseCase()
                fillUserUiValues(user!!)
                loadUserResult = UserResult.Success
            }
            catch (e: Exception){
                loadUserResult = UserResult.Error(e.message ?: "Ошибка сервера.")
            }
        }
    }

    fun changeUser(){
        val userData = UserChange(
            firstName = uiState.firstName,
            lastName = uiState.lastName,
            middleName = uiState.middleName,
            telegramName = uiState.telegramName
        )
        changeUserResult = UserResult.Loading

        viewModelScope.launch {
            try {
                usersRepository.changeUser(
                    userId = user!!.id,
                    user = userData
                )
                changeUserResult = UserResult.Success
            }
            catch (e: Exception){
                changeUserResult = UserResult.Error(e.message ?: "Ошибка")
            }

        }
        changeUserResult = UserResult.Idle
    }

    fun logOut(){
        logOutUseCase()
    }

    private fun fillUserUiValues(user: UserInfo){
        uiState = uiState.copy(
            email = user.email,
            firstName = user.firstName,
            lastName = user.lastName,
            middleName = user.middleName,
            telegramName = user.telegramName,
        )
    }
}
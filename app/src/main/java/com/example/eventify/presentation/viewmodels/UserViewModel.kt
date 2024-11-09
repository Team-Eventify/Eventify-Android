package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.eventify.data.models.UserInfo
import com.example.eventify.data.repositories.users.UsersRepository
import com.example.eventify.domain.usecases.CurrentUserUseCase
import com.example.eventify.presentation.models.UserResult
import com.example.eventify.presentation.models.UserUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class UserViewModel @Inject constructor(
    val currentUser: CurrentUserUseCase,
    val usersRepository: UsersRepository
): ViewModel() {

    var uiState by mutableStateOf(UserUiState.default())
        private set

    var userResult by mutableStateOf<UserResult>(UserResult.Idle)

    var user by mutableStateOf<UserInfo?>(null)
        private set

    suspend fun loadUserInfo(){
        userResult = UserResult.Loading

        try {
            user = currentUser.getCurrentUser()
            userResult = UserResult.Success
        }
        catch (e: Exception){
            userResult = UserResult.Error(e.message ?: "Ошибка сервера.")
        }
    }
}
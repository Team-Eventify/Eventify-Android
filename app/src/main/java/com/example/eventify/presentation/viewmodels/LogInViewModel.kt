package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.utils.TokenManager
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.repositories.AuthUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthUserRepository,
    private val tokenManager: TokenManager
) : ViewModel() {

    var loginValue by mutableStateOf("")
    var passwordValue by mutableStateOf("")

    fun logIn(
        onSuccess: (() -> Unit)? = null,
        onError: ((String) -> Unit)? = null
    ) = viewModelScope.launch {
        val response = authRepository.logInUser(
            LogInRequestData(
                email = loginValue,
                password = passwordValue
            )
        )
        if (response.code() == 200){
            response.body()?.let {
                tokenManager.setRefreshToken(it.refreshToken)
                tokenManager.setAccessToken(it.accessToken)
            }
            onSuccess?.invoke()
        }
        else{
            onError?.invoke(response.message())
        }
    }

}

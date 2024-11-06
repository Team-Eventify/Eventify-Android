package com.example.eventify.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.remote.NetworkServiceFactory
import com.example.eventify.data.remote.api.AuthAPI
import com.example.eventify.data.remote.models.auth.LogInRequestData
import com.example.eventify.data.repositories.AuthUserRepository
import com.example.eventify.data.repositories.AuthUserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val authRepository: AuthUserRepository
) : ViewModel() {

    var loginValue by mutableStateOf("")
    var passwordValue by mutableStateOf("")

    fun logIn(
        onSuccess: (() -> Unit)? = null
    ) = viewModelScope.launch {
        val response = authRepository.logInUser(
            LogInRequestData(
                email = loginValue,
                password = passwordValue
            )
        )
        if (response.code() == 200){
            onSuccess?.invoke()
        }
    }

}

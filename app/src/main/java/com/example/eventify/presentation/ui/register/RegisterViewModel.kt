package com.example.eventify.presentation.ui.register

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.data.models.UserCreate
import com.example.eventify.data.remote.models.events.CreateEventRequest
import com.example.eventify.domain.usecases.RegisterUseCase
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val navigator: Navigator
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.default())
    val stateFlow: StateFlow<RegisterState> = _stateFlow.asStateFlow()


    fun changeLogin(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value
            )
        }
    }

    fun changePassword(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value
            )
        }
    }

    fun register(){
        viewModelScope.launch {
            registerUser()
        }
    }

    private suspend fun registerUser(){
        val userData = stateFlow.value.run{
            UserCreate(
                email = login,
                password = password
            )
        }
        registerUseCase(user = userData)
    }

    fun navigateToLogin(){
        viewModelScope.launch {
            navigator.navigate(AuthRouter.LogInRoute)
        }
    }

}
package com.example.eventify.presentation.ui.auth.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventify.R
import com.example.eventify.domain.models.UserCredentials
import com.example.eventify.domain.DataError
import com.example.eventify.domain.Result
import com.example.eventify.domain.usecases.auth.LoginUseCase
import com.example.eventify.presentation.ui.auth.login.state.LogInState
import com.example.eventify.presentation.ui.auth.login.state.SideEffect
import com.example.eventify.presentation.utils.asUiText
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class LogInViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    @ApplicationContext private val context: Context
) : ViewModel() {
    private val mutableSideEffect = Channel<SideEffect>()
    val sideEffect: Flow<SideEffect> = mutableSideEffect.receiveAsFlow()

    private val _stateFlow: MutableStateFlow<LogInState> = MutableStateFlow(LogInState.default())
    val stateFlow: StateFlow<LogInState> = _stateFlow.asStateFlow()

    fun changeLogin(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                login = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun changePassword(value: String) {
        _stateFlow.update { currentState ->
            currentState.copy(
                password = value,
                hasLoginError = false,
                hasPasswordError = false,
            )
        }
    }

    fun logIn(){
        if (!validateFormData()) return

        val userCredentials = stateFlow.value.run {
            UserCredentials(
                login = login,
                password = password
            )
        }

        viewModelScope.launch {
            when (val result = loginUseCase(credentials = userCredentials)){
                is Result.Error -> handleErrors(result.error)
                is Result.Success -> {
                    mutableSideEffect.send(SideEffect.SuccessLogIn)
                }
            }
        }
    }

    private fun handleErrors(error: DataError){
        _stateFlow.update { currentState ->
            currentState.copy(
                hasPasswordError = true,
                hasLoginError = true,
            )
        }
        when (error){
            is DataError.Network -> {
                when (error){
                    DataError.Network.NOT_FOUND -> mutableSideEffect.trySend(SideEffect.UnsuccessLogIn(context.getString(R.string.user_not_found)))
                    else -> mutableSideEffect.trySend(SideEffect.UnsuccessLogIn(error.asUiText().asString(context = context)))
                }
            }
            else -> mutableSideEffect.trySend(SideEffect.UnsuccessLogIn(error.asUiText().asString(context = context)))
        }
    }

    private fun validateFormData(): Boolean{
        return _stateFlow.value.run { isValidLogin && isValidPassword  }
    }
}
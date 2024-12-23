package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.eventify.R
import com.example.eventify.domain.validation.ValidateEmail
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
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
class ResetPasswordViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val sharedEmail = savedStateHandle.toRoute<AuthRouter.ResetPasswordRoute>().email

    private val validateEmail = ValidateEmail()

    private val _stateFlow: MutableStateFlow<ResetPasswordState> =
        MutableStateFlow(ResetPasswordState(
            email = sharedEmail ?: ""
        ))

    val stateFlow: StateFlow<ResetPasswordState> = _stateFlow.asStateFlow()

    fun onChangeEmail(value: String){
        _stateFlow.update { currentState ->
            currentState.copy(
                email = value
            )
        }
    }

    fun resetPassword(){
        viewModelScope.launch {
            SnackbarController.sendEvent(
                SnackbarEvent(
                    message = "Will be later"
                )
            )
        }
    }
}
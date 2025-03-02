package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.LocalTopBarState

@Composable
fun RegisterRoute(
    coordinator: RegisterCoordinator = rememberRegisterCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState()
    val actions = rememberRegisterActions(coordinator)
    val topBarState = LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    RegisterScreen(uiState, actions)
}


@Composable
fun rememberRegisterActions(coordinator: RegisterCoordinator): RegisterActions {
    return remember(coordinator) {
        RegisterActions(
            navigateToLogIn = coordinator.viewModel::navigateToLogin,
            onChangeLogin = coordinator.viewModel::changeLogin,
            onChangePassword = coordinator.viewModel::changePassword,
            onRegister = coordinator.viewModel::register,
            onChangeOtp = coordinator.viewModel::changeOtp,
            onRequestOtp = coordinator.viewModel::requestOtp,
            onTriggerOtpBottomSheet = coordinator.viewModel::triggerOtpBottomSheet
        )
    }
}
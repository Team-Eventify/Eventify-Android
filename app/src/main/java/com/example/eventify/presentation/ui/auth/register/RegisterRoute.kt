package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.models.ScaffoldViewState

@Composable
fun RegisterRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: RegisterCoordinator = rememberRegisterCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberRegisterActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = ScaffoldViewState(
            showBottomBar = false
        )
    }

    // UI Rendering
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
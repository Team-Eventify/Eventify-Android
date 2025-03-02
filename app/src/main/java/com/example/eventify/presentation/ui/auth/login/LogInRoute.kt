package com.example.eventify.presentation.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState

@Composable
fun LogInRoute(
    coordinator: LogInCoordinator = rememberLogInCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val actions = rememberLogInActions(coordinator)
    val topBarState = LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    // UI Rendering
    LogInScreen(uiState, actions)
}


@Composable
fun rememberLogInActions(coordinator: LogInCoordinator): LogInActions {
    return remember(coordinator) {
        LogInActions(
            onChangeLogin = coordinator.viewModel::changeLogin,
            onChangePassword = coordinator.viewModel::changePassword,
            onSubmit = coordinator.viewModel::logIn,
            navigateToRegister = coordinator::navigateToRegister,
            navigateToResetPassword = coordinator.viewModel::navigateToResetPassword
        )
    }
}
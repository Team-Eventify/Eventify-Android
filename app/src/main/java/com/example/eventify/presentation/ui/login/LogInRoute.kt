package com.example.eventify.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun LogInRoute(
    navController: NavHostController,
    coordinator: LogInCoordinator = rememberLogInCoordinator(navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberLogInActions(coordinator)

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
            navigateToRegister = coordinator::navigateToRegister
        )
    }
}
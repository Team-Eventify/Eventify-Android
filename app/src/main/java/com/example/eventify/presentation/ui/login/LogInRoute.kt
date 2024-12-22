package com.example.eventify.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.models.ScaffoldViewState

@Composable
fun LogInRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: LogInCoordinator = rememberLogInCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberLogInActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = ScaffoldViewState(
            showBottomBar = false
        )
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
            navigateToRegister = coordinator::navigateToRegister
        )
    }
}
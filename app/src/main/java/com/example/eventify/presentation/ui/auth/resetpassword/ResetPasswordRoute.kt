package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ResetPasswordRoute(
    coordinator: ResetPasswordCoordinator = rememberResetPasswordCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(ResetPasswordState())

    // UI Actions
    val actions = rememberResetPasswordActions(coordinator)

    // UI Rendering
    ResetPasswordScreen(uiState, actions)
}


@Composable
fun rememberResetPasswordActions(coordinator: ResetPasswordCoordinator): ResetPasswordActions {
    return remember(coordinator) {
        ResetPasswordActions(
            onSubmit = coordinator.viewModel::resetPassword,
            onChangeEmail = coordinator.viewModel::onChangeEmail
        )
    }
}
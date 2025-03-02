package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.LocalTopBarState

@Composable
fun ResetPasswordRoute(
    coordinator: ResetPasswordCoordinator = rememberResetPasswordCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(ResetPasswordState())
    val actions = rememberResetPasswordActions(coordinator)
    val topBarState = LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

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
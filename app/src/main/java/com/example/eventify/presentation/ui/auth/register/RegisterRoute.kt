package com.example.eventify.presentation.ui.auth.register

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.example.eventify.presentation.LocalTopBarState

@Composable
fun RegisterRoute(
    coordinator: RegisterCoordinator = rememberRegisterCoordinator()
) {
    val ctx = LocalContext.current
    val uiState by coordinator.screenStateFlow.collectAsState()
    val actions = rememberRegisterActions(coordinator, ctx)
    val topBarState = LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.hide()
    }


    RegisterScreen(uiState, actions)
}


@Composable
fun rememberRegisterActions(coordinator: RegisterCoordinator, ctx: Context): RegisterActions {
    return remember(coordinator) {
        RegisterActions(
            navigateToLogIn = coordinator.viewModel::navigateToLogin,
            onChangeLogin = coordinator.viewModel::changeLogin,
            onChangePassword = coordinator.viewModel::changePassword,
            onRegister = coordinator.viewModel::register,
            onChangeOtp = coordinator.viewModel::changeOtp,
            onRequestOtp = coordinator.viewModel::requestOtp,
            onTriggerOtpBottomSheet = coordinator.viewModel::triggerOtpBottomSheet,
            navigateToPrivacyPolicy = { openDeepLink(ctx, "https://nometa.xyz") }
        )
    }
}

fun openDeepLink(ctx: Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    ctx.startActivity(intent)
}
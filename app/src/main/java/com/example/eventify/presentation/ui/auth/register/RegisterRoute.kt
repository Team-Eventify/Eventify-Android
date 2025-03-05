package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.entries.auth.LoginEntry
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.auth.register.state.RegisterListener
import com.example.eventify.presentation.ui.auth.register.state.SideEffect
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun RegisterRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features
    val snackBarState = LocaleSnackbarState.current

    val listener = object : RegisterListener {
        override fun navigateToLogIn() {
            features.navigateToFeature<LoginEntry>(navController)
        }

        override fun onChangeLogin(login: String) {
            viewModel.changeLogin(login)
        }

        override fun onChangePassword(password: String) {
            viewModel.changePassword(password)
        }

        override fun onRequestOtp() {
            viewModel.requestOtp()
        }

        override fun onRegister() {
            viewModel.register()
        }

        override fun onChangeOtp(otpValue: String) {
            viewModel.changeOtp(otpValue)
        }

        override fun onTriggerOtpBottomSheet(value: Boolean) {
            viewModel.triggerOtpBottomSheet(value)
        }

    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.SuccessRegister -> {
//                features.navigateToFeature<>()
            }
            is SideEffect.FailRegister -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: ""
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    RegisterScreen(uiState, listener)
}

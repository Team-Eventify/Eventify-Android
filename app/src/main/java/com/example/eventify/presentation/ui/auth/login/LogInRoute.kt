package com.example.eventify.presentation.ui.auth.login

import androidx.compose.material3.SnackbarVisuals
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.navigation.ARG_SHARED_EMAIL
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.entries.auth.LoginEntry
import com.example.eventify.presentation.navigation.entries.auth.RegisterEntry
import com.example.eventify.presentation.navigation.entries.auth.ResetPasswordEntry
import com.example.eventify.presentation.navigation.entries.events.EventsFeedFeatureEntry
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.auth.login.state.LoginListener
import com.example.eventify.presentation.ui.auth.login.state.SideEffect
import com.example.eventify.presentation.utils.ObserveAsEvent
import com.example.eventify.presentation.utils.ObserveAsState
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun LogInRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<LogInViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val snackBarState = LocaleSnackbarState.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : LoginListener {
        override fun onChangeLogin(login: String) {
            viewModel.changeLogin(login)
        }

        override fun onChangePassword(password: String) {
            viewModel.changePassword(password)
        }

        override fun onSubmit() {
            viewModel.logIn()
        }

        override fun navigateToRegister() {
            features.navigateToFeature<RegisterEntry>(navController)
        }

        override fun navigateToResetPassword(sharedEmail: String?) {
            features.navigateToFeature<ResetPasswordEntry>(
                navController,
                ARG_SHARED_EMAIL to sharedEmail
            )

        }
    }


    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.ServerError -> TODO()
            SideEffect.SuccessLogIn -> {
                features.navigateNewTaskFeature<EventsFeedFeatureEntry, LoginEntry>(navController)
            }
            is SideEffect.UnsuccessLogIn -> {
                snackBarState.showSnackbar(
                        message = sideEffect.message ?: "error"
                    )
            }
        }
    }


    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    // UI Rendering
    LogInScreen(uiState, listener)
}

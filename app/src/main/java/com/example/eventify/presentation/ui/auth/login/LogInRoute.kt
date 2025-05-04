package com.example.eventify.presentation.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalSnackbarState
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.ui.common.snackbar.SnackbarType
import com.example.eventify.presentation.navigation.ARG_SHARED_EMAIL
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.ui.auth.register.RegisterEntry
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntry
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.auth.login.state.LoginListener
import com.example.eventify.presentation.ui.auth.login.state.SideEffect
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun LogInRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<LogInViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val snackBarState = LocalSnackbarState.current
    val context = LocalContext.current
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
            features.navigateToFeature<ResetPasswordEntry>(navController) {
                query {
                    put(ARG_SHARED_EMAIL, sharedEmail)
                }
            }

        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {

            SideEffect.ServerError -> {
                snackBarState.show(
                    message = context.getString(R.string.server_error),
                    type = SnackbarType.Error,
                    force = true,
                )
            }
            SideEffect.SuccessLogIn -> {
                features.navigateNewTaskFeature<EventsFeedEntry, LoginEntry>(navController)
            }
            SideEffect.UnsuccessLogIn -> {
                snackBarState.show(
                    message = context.getString(R.string.incorrect_auth_data),
                    type = SnackbarType.Error,
                    force = true,
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

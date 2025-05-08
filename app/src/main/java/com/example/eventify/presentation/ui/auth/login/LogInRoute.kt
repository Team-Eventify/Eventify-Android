package com.example.eventify.presentation.ui.auth.login

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.domain.services.AccountCredentialManager
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
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
import kotlinx.coroutines.launch

@Composable
fun LogInRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<LogInViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val snackBarState = LocaleSnackbarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features
    val scope = rememberCoroutineScope()
    val credentialManager = remember {
        AccountCredentialManager(context as ComponentActivity)
    }

    LaunchedEffect(Unit) {
        scope.launch {
            credentialManager.signIn { login, password ->
                viewModel.logIn(
                    login = login,
                    password = password,
                )
            }
        }
    }

    val listener = object : LoginListener {
        override fun onChangeLogin(login: String) {
            viewModel.changeLogin(login)
        }

        override fun onChangePassword(password: String) {
            viewModel.changePassword(password)
        }

        override fun login(login: String, password: String) {
            viewModel.logIn(login, password)
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
                snackBarState.showSnackbar(
                    message = context.getString(R.string.server_error)
                )
            }
            SideEffect.SuccessLogIn -> {
                features.navigateNewTaskFeature<EventsFeedEntry, LoginEntry>(navController)
            }
            SideEffect.UnsuccessLogIn -> {
                snackBarState.showSnackbar(
                    message = context.getString(R.string.incorrect_auth_data)
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

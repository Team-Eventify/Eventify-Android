package feature.login.impl

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import feature.login.api.LoginEntry
import feature.login.api.LoginListener
import feature.login.impl.state.SideEffect
import feature.register.api.RegisterEntry
import kotlinx.coroutines.launch

@Composable
internal fun LogInRoute(
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

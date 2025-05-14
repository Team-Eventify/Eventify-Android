package feature.login.impl.ui

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
import core.common.navigation.ARG_SHARED_EMAIL
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import core.featureManager.navigateNewTaskFeature
import domain.AccountCredentialManager
import feature.eventFeed.api.EventsFeedEntry
import feature.login.api.LoginEntry
import feature.login.api.LoginListener
import feature.login.impl.LogInViewModel
import feature.login.impl.state.SideEffect
import feature.register.api.RegisterEntry
import feature.resetPassword.api.ResetPasswordEntry
import kotlinx.coroutines.launch
import uikit.LocalSnackbarState
import uikit.components.snackbar.SnackbarType
import uikit.components.topBar.LocalTopBarState
import uikit.utils.ObserveAsEvent
import com.example.eventify.uikit.R as UiKitR

@Composable
internal fun LogInRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<LogInViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val snackBarState = LocalSnackbarState.current
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
                snackBarState.show(
                    message = context.getString(UiKitR.string.server_error),
                    type = SnackbarType.Error
                )
            }
            SideEffect.SuccessLogIn -> {
                features.navigateNewTaskFeature<EventsFeedEntry, LoginEntry>(navController)
            }
            SideEffect.UnsuccessLogIn -> {
                snackBarState.show(
                    message = context.getString(UiKitR.string.incorrect_auth_data),
                    type = SnackbarType.Error
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

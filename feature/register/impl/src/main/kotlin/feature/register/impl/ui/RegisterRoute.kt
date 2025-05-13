package feature.register.impl.ui

import android.content.Intent
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
import kotlinx.coroutines.launch
import androidx.core.net.toUri
import domain.AccountCredentialManager
import feature.login.api.LoginEntry
import feature.register.api.RegisterListener
import feature.register.impl.RegisterViewModel
import feature.register.impl.state.SideEffect
import uikit.LocaleSnackbarState
import uikit.components.topBar.LocalTopBarState
import uikit.utils.ObserveAsEvent
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import com.example.eventify.uikit.R as UiKitR

@Composable
fun RegisterRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<RegisterViewModel>()
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features
    val snackBarState = LocaleSnackbarState.current
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val credentialManager = remember {
        AccountCredentialManager(context as ComponentActivity)
    }


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

        override fun onRequestOtp(login: String, password: String) {
            viewModel.register(
                login = login,
                password = password,
            )
        }

        override fun onRegister(login: String, password: String, otp: String) {
            viewModel.validateOtp(
                login = login,
                password = password,
                otp = otp,
            )
        }

        override fun onChangeOtp(otpValue: String) {
            viewModel.updateOtp(otpValue)
        }

        override fun onTriggerOtpBottomSheet(value: Boolean) {
            viewModel.triggerOtpBottomSheet(value)
        }

        override fun goToPrivacyPolicy() {
            val intent = Intent(Intent.ACTION_VIEW, "https://nometa.xyz".toUri())
            context.startActivity(intent)
        }

    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.SuccessRegister -> {
                viewModel.triggerOtpBottomSheet(false)
                scope.launch {
                    credentialManager.signUp(
                        login = uiState.payloadState.login,
                        password = uiState.payloadState.password,
                    )
                    features.navigateToFeature<SetUpEntry>(navController)
                }
            }
            is SideEffect.FailRegister -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: ""
                )
            }

            SideEffect.ServerError -> {
                snackBarState.showSnackbar(
                    message = context.getString(UiKitR.string.server_error)
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    RegisterScreen(uiState, listener)
}

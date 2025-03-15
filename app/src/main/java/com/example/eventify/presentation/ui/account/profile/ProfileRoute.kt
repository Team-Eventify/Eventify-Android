package com.example.eventify.presentation.ui.account.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.clearNavigate
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditEntry
import com.example.eventify.presentation.ui.settings.aboutapp.AboutAppEntry
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.account.profile.components.LoadingProfile
import com.example.eventify.presentation.ui.account.profile.state.ProfileListener
import com.example.eventify.presentation.ui.account.profile.state.SideEffect
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.ui.account.profile_decor.ProfileDecorEntry
import com.example.eventify.presentation.ui.auth.login.LoginEntry
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun ProfileRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features


    val listener = object : ProfileListener {
        override fun onLogOut() {
            viewModel.logOut()
        }

        override fun navigateToProfileEdit() {
            features.navigateToFeature<ProfileEditEntry>(navController)
        }

        override fun navigateToAppInfo() {
            features.navigateToFeature<AboutAppEntry>(navController)
        }

        override fun navigateToDecor() {
            features.navigateToFeature<ProfileDecorEntry>(navController)
        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.LogOut -> {
                features.clearNavigate<LoginEntry>(navController)
            }
        }
    }

    when (uiState) {
        is UiState.Error -> {
            ErrorScreen(
                title = stringResource(R.string.failed_load_profile),
                description = (uiState as UiState.Error).message
            )
        }
        UiState.Loading -> LoadingProfile()
        is UiState.ShowProfile -> {
            ProfileScreen((uiState as UiState.ShowProfile), listener)

        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.profile),
                size = TopBarSize.SMALL,
            )
        )
    }

}

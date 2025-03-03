package com.example.eventify.presentation.ui.account.profile

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.account.profile.state.ProfileListener
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar

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
//            features.navigateToFeature<>(navController)
        }

        override fun navigateToProfileEdit() {
//            features.navigateToFeature<>(navController)
        }

        override fun navigateToAppInfo() {
//            features.navigateToFeature<>(navController)
        }
    }

    when (uiState) {
        UiState.Error -> {}
        UiState.Loading -> {}
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

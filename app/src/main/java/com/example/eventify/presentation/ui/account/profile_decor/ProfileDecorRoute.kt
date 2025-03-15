package com.example.eventify.presentation.ui.account.profile_decor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.account.profile.components.LoadingProfile
import com.example.eventify.presentation.ui.account.profile.state.UiState
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.common.screens.ErrorScreen

@Composable
fun ProfileDecorRoute(navController: NavController) {
    val viewModel = hiltViewModel<ProfileDecorViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    val listener = object : ProfileDecorRouteListener {
        override fun onBackClick() {
            navController.navigateUp()
        }
    }

    when (uiState) {
        is UiState.Loading -> LoadingProfile()
        is UiState.Error -> ErrorScreen(
                title = stringResource(R.string.failed_load_profile),
                description = (uiState as UiState.Error).message
        )
        is UiState.ShowProfile -> {
            ProfileDecorScreen()
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.design_section_title),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
}
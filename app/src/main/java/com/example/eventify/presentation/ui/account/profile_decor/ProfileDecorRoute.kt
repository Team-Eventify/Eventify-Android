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
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorUiState
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
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
        override fun changeTheme(typeOfTheme: TypesTheme) {
            viewModel.changeTheme(typeOfTheme)
        }
    }

    when (uiState) {
        is ProfileDecorUiState.Loading -> ProfileDecorScreen(uiState, listener)
        is ProfileDecorUiState.Error -> ErrorScreen(
            title = stringResource(R.string.failed_load_profile)
        )
        is ProfileDecorUiState.ProfileDecorData -> {
            ProfileDecorScreen(uiState, listener)
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
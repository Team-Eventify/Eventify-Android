package com.example.eventify.presentation.ui.account.aboutapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar


@Composable
fun AboutAppRoute(
    coordinator: AboutAppCoordinator = rememberAboutAppCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(AboutAppState())
    val actions = rememberAboutAppActions(coordinator)
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.about_app),
                size = TopBarSize.SMALL,
            )
        )
    }

    AboutAppScreen(uiState, actions)
}


@Composable
fun rememberAboutAppActions(coordinator: AboutAppCoordinator): AboutAppActions {
    return remember(coordinator) {
        AboutAppActions(
            navigateUp = coordinator.viewModel::navigateUp,
            goPrivacyPolicy = coordinator.viewModel::navigateToPrivacyPolicy,
            goToAboutUs = coordinator.viewModel::navigateToAboutUs,
            goToDonate = coordinator.viewModel::navigateToDonate,
            goTermsOfUse = coordinator.viewModel::navigateTermsOfUse,
            goToInformationSecurity = coordinator.viewModel::navigateToInformationSecurity
        )
    }
}

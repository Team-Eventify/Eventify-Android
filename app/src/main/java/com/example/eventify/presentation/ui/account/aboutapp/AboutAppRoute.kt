package com.example.eventify.presentation.ui.account.aboutapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.eventify.R
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar


@Composable
fun AboutAppRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: AboutAppCoordinator = rememberAboutAppCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(AboutAppState())

    // UI Actions
    val actions = rememberAboutAppActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            topBar = {
                DefaultTopAppBar(
                    title = stringResource(R.string.about_app),
                    onNavigateUp = actions.navigateUp
                )
            }, showBottomBar = false
        )
    }

    // UI Rendering
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

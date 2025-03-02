package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.LocalTopBarState


@Composable
fun OnBoardingRoute(
    coordinator: OnBoardingCoordinator = rememberOnBoardingCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(OnBoardingState())
    val actions = rememberOnBoardingActions(coordinator)
    val topBarState = LocalTopBarState.current

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    OnBoardingScreen(uiState, actions)
}


@Composable
fun rememberOnBoardingActions(coordinator: OnBoardingCoordinator): OnBoardingActions {
    return remember(coordinator) {
        OnBoardingActions(
            onFinishOnboarding = coordinator.viewModel::finishOnboarding
        )
    }
}

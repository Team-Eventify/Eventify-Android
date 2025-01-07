package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.models.ScaffoldViewState


@Composable
fun OnBoardingRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: OnBoardingCoordinator = rememberOnBoardingCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(OnBoardingState())

    // UI Actions
    val actions = rememberOnBoardingActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            topBar = {},
            showBottomBar = false
        )
    }

    // UI Rendering
    OnBoardingScreen(uiState, actions)
}


@Composable
fun rememberOnBoardingActions(coordinator: OnBoardingCoordinator): OnBoardingActions {
    return remember(coordinator) {
        OnBoardingActions(
            onLeaveFromOnboarding = coordinator.viewModel::leaveFromOnboarding
        )
    }
}

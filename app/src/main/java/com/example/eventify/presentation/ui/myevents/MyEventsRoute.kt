package com.example.eventify.presentation.ui.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun MyEventsRoute(
    coordinator: MyEventsCoordinator = rememberMyEventsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberMyEventsActions(coordinator)

    // UI Rendering
    if (uiState.finishedEvents.isEmpty() && uiState.upComingEvents.isEmpty()){
        EmptyMyEventsScreen()
    } else {
        MyEventsScreen(uiState, actions)
    }
}


@Composable
fun rememberMyEventsActions(coordinator: MyEventsCoordinator): MyEventsActions {
    return remember(coordinator) {
        MyEventsActions(
            onRefresh = coordinator.viewModel::refresh,
            navigateToEvent = coordinator.viewModel::navigateToEvent
        )
    }
}
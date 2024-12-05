package com.example.eventify.presentation.ui.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController

@Composable
fun EventDetailRoute(
    navController: NavHostController,
    coordinator: EventDetailCoordinator = rememberEventDetailCoordinator(navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberEventDetailActions(coordinator)

    // UI Rendering
    if (uiState.event != null){
        EventDetailScreen(uiState, actions)
    }
}


@Composable
fun rememberEventDetailActions(coordinator: EventDetailCoordinator): EventDetailActions {
    return remember(coordinator) {
        EventDetailActions(
            navigateUp = coordinator::navigateUp
        )
    }
}
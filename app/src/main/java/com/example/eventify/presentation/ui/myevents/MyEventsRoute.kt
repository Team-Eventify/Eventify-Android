package com.example.eventify.presentation.ui.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.shared.DefaultTopAppBar

@Composable
fun MyEventsRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: MyEventsCoordinator = rememberMyEventsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState()

    // UI Actions
    val actions = rememberMyEventsActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = true,
            topBar = {
                DefaultTopAppBar(
                    title = "My Events"
                )
            }
        )
    }

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
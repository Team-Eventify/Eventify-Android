package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.R
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.myevents.components.EmptyMyEventsScreen
import com.example.eventify.presentation.ui.events.myevents.components.LoadingMyEvents

@Composable
fun MyEventsRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: MyEventsCoordinator = rememberMyEventsCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberMyEventsActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = true,
            topBar = {
                DefaultTopAppBar(
                    title = stringResource(R.string.my_events_title)
                )
            }
        )
    }

    // UI Rendering
    when (uiState) {
        UiState.Initial -> LoadingMyEvents()
        is UiState.Empty -> EmptyMyEventsScreen(
            onActionClick = actions.navigateToFeed,
        )
        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_loading_my_events),
            description = (uiState as UiState.Error).message
        )
        is UiState.ShowMyEvents -> {
            LaunchedEffect(Unit) {
                scaffoldViewState.value = scaffoldViewState.value.copy(
                    showBottomBar = true,
                    topBar = {
                        DefaultTopAppBar(
                            title = stringResource(R.string.my_events_title)
                        )
                    }
                )
            }
            MyEventsScreen(uiState as UiState.ShowMyEvents, actions)
        }
    }
}


@Composable
fun rememberMyEventsActions(coordinator: MyEventsCoordinator): MyEventsActions {
    return remember(coordinator) {
        MyEventsActions(
            onRefresh = coordinator.viewModel::refresh,
            navigateToEvent = coordinator.viewModel::navigateToEvent,
            navigateToFeedback = coordinator.viewModel::navigateToFeedback,
            navigateToFeed = coordinator.viewModel::navigateToFeed
        )
    }
}
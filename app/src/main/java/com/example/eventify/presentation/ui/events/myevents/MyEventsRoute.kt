package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.myevents.components.EmptyMyEventsScreen
import com.example.eventify.presentation.ui.events.myevents.components.LoadingMyEvents

@Composable
fun MyEventsRoute(
    coordinator: MyEventsCoordinator = rememberMyEventsCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val actions = rememberMyEventsActions(coordinator)
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    // UI Rendering
    when (uiState) {
        UiState.Initial -> {
            LoadingMyEvents()
        }
        is UiState.Empty -> EmptyMyEventsScreen(
            onActionClick = actions.navigateToFeed,
        )
        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_loading_my_events),
            description = (uiState as UiState.Error).message
        )
        is UiState.ShowMyEvents -> {
            MyEventsScreen(uiState as UiState.ShowMyEvents, actions)
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.my_events_title),
                size = TopBarSize.SMALL,
            )
        )
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
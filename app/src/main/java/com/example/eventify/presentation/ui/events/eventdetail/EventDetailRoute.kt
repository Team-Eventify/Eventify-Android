package com.example.eventify.presentation.ui.events.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.events.eventdetail.components.EventDetailTopAppBar

@Composable
fun EventDetailRoute(
    navController: NavHostController,
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: EventDetailCoordinator = rememberEventDetailCoordinator(navController)
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberEventDetailActions(coordinator)

    when (uiState) {
        is UiState.Error -> {}
        is UiState.ShowEvent -> {
            LaunchedEffect(Unit) {
                scaffoldViewState.value = scaffoldViewState.value.copy(
                    showBottomBar = false,
                    topBar = {
                        EventDetailTopAppBar(
                            title = (uiState as UiState.ShowEvent).event.eventInfo.title,
                            onNavigateUp = actions.navigateUp
                        )
                    }
                )
            }
            EventDetailScreen(uiState as UiState.ShowEvent, actions, coordinator.viewModel.imageLoader)
        }

        UiState.Loading -> {}
    }
}


@Composable
fun rememberEventDetailActions(coordinator: EventDetailCoordinator): EventDetailActions {
    return remember(coordinator) {
        EventDetailActions(
            navigateUp = coordinator::navigateUp,
            onSubscribe = coordinator.viewModel::subscribeForEvent,
            onUnsubscribe = coordinator.viewModel::unsubscribeForEvent,
            goToRatePage = coordinator.viewModel::navigateToRate
        )
    }
}
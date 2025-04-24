package com.example.eventify.presentation.ui.events.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventdetail.components.LoadingEvent
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailListener
import com.example.eventify.presentation.ui.events.eventdetail.state.SideEffect
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailUiState
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun EventDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<EventDetailViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val snackBarState = LocaleSnackbarState.current

    val listener = object : EventDetailListener {
        override fun navigateUp() {
            navController.navigateUp()
        }

        override fun onActionClick() {
            viewModel.primaryAction()
        }

        override fun goToRatePage() {
            TODO("Not yet implemented")
        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.SuccessSubscribeEvent -> {
                navController.navigateUp()
                snackBarState.showSnackbar(
                    message = "Вы подписались на событие"
                )
            }
            SideEffect.SuccessUnsubscribeEvent -> {
                navController.navigateUp()
                snackBarState.showSnackbar(
                    message = "Вы отписались от события"
                )
            }
            is SideEffect.FailSubscribeEvent -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: "Не удалось подписаться на событие"
                )
            }
            is SideEffect.FailUnsubscribeEvent -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: "Не удалось отписаться от события"
                )
            }
        }
    }

    when (uiState) {
        EventDetailUiState.Loading -> LoadingEvent()

        is EventDetailUiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_load_event),
            description = (uiState as EventDetailUiState.Error).message
        )
        is EventDetailUiState.ShowEvent -> {
            EventDetailScreen(uiState as EventDetailUiState.ShowEvent, listener)
        }
    }
}


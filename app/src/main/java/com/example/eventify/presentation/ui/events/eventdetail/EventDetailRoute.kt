package com.example.eventify.presentation.ui.events.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventdetail.components.LoadingEvent
import com.example.eventify.presentation.ui.events.eventdetail.state.EventDetailListener
import com.example.eventify.presentation.ui.events.eventdetail.state.UiState

@Composable
fun EventDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<EventDetailViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
//    val features = LocalFeaturesProvider.current.features

    val listener = object : EventDetailListener {
        override fun navigateUp() {
            navController.navigateUp()
        }

        override fun onSubscribe() {
            TODO("Not yet implemented")
        }

        override fun onUnsubscribe() {
            TODO("Not yet implemented")
        }

        override fun goToRatePage() {
            TODO("Not yet implemented")
        }
    }

    when (uiState) {
        UiState.Loading -> LoadingEvent()

        is UiState.Error -> ErrorScreen(
            title = stringResource(R.string.error_load_event),
            description = (uiState as UiState.Error).message
        )
        is UiState.ShowEvent -> {
            EventDetailScreen(uiState as UiState.ShowEvent, listener)
        }
    }
}


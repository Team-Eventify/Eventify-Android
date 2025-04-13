package com.example.eventify.presentation.ui.searchresult

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.navigateToFeature
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntry
import com.example.eventify.presentation.ui.events.search.state.EventId
import com.example.eventify.presentation.ui.searchresult.components.EventsSearchDetail
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailListener
import com.example.eventify.presentation.ui.searchresult.state.SearchDetailUiState


@Composable
fun SearchDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<SearchDetailViewModel>()
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val features = LocalFeaturesProvider.current.features
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    val listener = object : SearchDetailListener {
        override fun onBackClick() {
            navController.popBackStack()
        }

        override fun onEventClick(eventId: EventId) {
            features.navigateToFeature<EventDetailEntry>(navController) {
                path {
                    put(ARG_EVENT_ID, eventId)
                }
            }
        }

    }

    when (state) {
        SearchDetailUiState.Loading -> {}

        SearchDetailUiState.NotFound -> ErrorScreen(
            title = stringResource(R.string.not_found),
        )

        is SearchDetailUiState.Error -> ErrorScreen(
            title = stringResource(R.string.failed_to_load_data),
            description = (state as SearchDetailUiState.Error).message,
        )

        is SearchDetailUiState.ShowEvents -> EventsSearchDetail(
            state = state as SearchDetailUiState.ShowEvents,
            actions = listener,
        )
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.search),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
}
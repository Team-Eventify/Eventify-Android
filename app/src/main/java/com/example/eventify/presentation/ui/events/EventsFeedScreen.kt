package com.example.eventify.presentation.ui.events

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.R
import com.example.eventify.data.models.EventInfo
import com.example.eventify.presentation.models.EventFeedResult
import com.example.eventify.presentation.models.EventFeedUiState
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.navigation.HomeRouter
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.viewmodels.EventsViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun EventsFeedScreen(
    navController: NavHostController,
    goToEventDetail: (String) -> Unit,
    viewModel: EventsViewModel = hiltViewModel()
) {
    EventsFeedComponent(
        onLoadEvents = viewModel::loadEvents,
        onRefreshEvents = viewModel::refresh,
        events = viewModel.events,
        goToEventDetail = goToEventDetail,
        eventFeedResult = viewModel.result,
        navController = navController,
        uiState = viewModel.uiState
    )
}


@Composable
fun EventsFeedComponent(
    onLoadEvents: () -> Unit,
    onRefreshEvents: () -> Unit,
    events: List<ShortEventItem>,
    goToEventDetail: (String) -> Unit,
    uiState: EventFeedUiState,
    eventFeedResult: EventFeedResult,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    val swipeRefreshState = rememberSwipeRefreshState(eventFeedResult is EventFeedResult.Refreshing)

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = onRefreshEvents
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            modifier = modifier
                .fillMaxSize()
                .padding(15.dp)
                .verticalScroll(rememberScrollState())
        ) {
            HeadingText(stringResource(R.string.popular_events))

            events.forEach { event ->
                EventCard(
                    title = event.title,
                    description = event.description,
                    modifier = modifier
                        .animateContentSize()
                        .clickable {
                            goToEventDetail(event.id)
                        }
                )
                Divider()
            }

            HeadingText(stringResource(R.string.categories_based_on_interests))



            HeadingText("Ивенты, которые тебе понравятся")

        }
    }


}

@Preview(name = "EventsFeedScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsFeedScreen() {
    EventsFeedComponent(
        events = emptyList(

        ),
        onLoadEvents = {},
        onRefreshEvents = {},
        goToEventDetail = {},
        navController = rememberNavController(),
        eventFeedResult = EventFeedResult.Idle,
        uiState = EventFeedUiState.default()
    )
}
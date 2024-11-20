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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.R
import com.example.eventify.data.models.EventInfo
import com.example.eventify.presentation.models.ShortEventItem
import com.example.eventify.presentation.navigation.HomeRouter
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.viewmodels.EventsViewModel

@Composable
fun EventsFeedScreen(
    navController: NavHostController,
    goToEventDetail: (String) -> Unit,
    viewModel: EventsViewModel = hiltViewModel()
) {
    EventsFeedComponent(
        loadEvents = viewModel::loadEvents,
        events = viewModel.events,
        goToEventDetail = goToEventDetail,
        navController = navController
    )
}


@Composable
fun EventsFeedComponent(
    loadEvents: () -> Unit,
    events: List<ShortEventItem>,
    goToEventDetail: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
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

@Preview(name = "EventsFeedScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsFeedScreen() {
    EventsFeedComponent(
        events = emptyList(

        ),
        loadEvents = {},
        goToEventDetail = {},
        navController = rememberNavController()
    )
}
package com.example.eventify.presentation.ui.events

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.data.models.EventInfo
import com.example.eventify.presentation.ui.navgraphs.HomeRouter
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.shared.HeadingText
import com.example.eventify.presentation.viewmodels.EventsViewModel

@Composable
fun EventsFeedScreen(
    navController: NavHostController,
    viewModel: EventsViewModel = hiltViewModel()
) {
    EventsFeedComponent(
        loadEvents = viewModel::loadEvents,
        events = viewModel.events,
        navController = navController
    )
}


@Composable
fun EventsFeedComponent(
    loadEvents: () -> Unit,
    events: List<EventInfo>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(true) {
        loadEvents()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
            .fillMaxSize()
            .padding(15.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeadingText("Популярные ивенты")

        events.forEach { event ->
            EventCard(
                title = event.title,
                description = event.description,
                modifier = modifier
                    .clickable {
                        navController.navigate(HomeRouter.EventDetail(event.id))
                    }
            )
            Divider()
        }

        HeadingText("Категории на основе твоих интересов")



        HeadingText("Ивенты, которые тебе понравятся")

    }
}

@Preview(name = "EventsFeedScreen", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewEventsFeedScreen() {
    EventsFeedComponent(
        events = emptyList(),
        loadEvents = {},
        navController = rememberNavController()
    )
}
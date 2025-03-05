package com.example.eventify.presentation.navigation.entries.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailRoute
import javax.inject.Inject

val EventDetailPath = EventsRootPath.updateAndGetPath("detail")

interface EventDetailEntry : ComposableFeatureEntry {
    override val route: String
        get() = EventDetailPath

    override val argumentsKeys: List<String>
        get() = listOf(
            ARG_EVENT_ID,
        )
}

class EventDetailEntryImpl @Inject constructor() : EventDetailEntry{
    @Composable
    override fun Composable(navController: NavHostController) {
        EventDetailRoute(navController)
    }

}
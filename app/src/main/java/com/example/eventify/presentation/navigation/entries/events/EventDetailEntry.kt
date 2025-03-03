package com.example.eventify.presentation.navigation.entries.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailRoute
import javax.inject.Inject

val EventDetailPath = EventsRootPath.updateAndGetPath("detail")

interface EventDetailEntry : ComposableFeatureEntry {
    override val route: String
        get() = EventDetailPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class EventDetailEntryImpl @Inject constructor() : EventDetailEntry{
    @Composable
    override fun Composable(navController: NavHostController) {
        EventDetailRoute(navController)
    }

}
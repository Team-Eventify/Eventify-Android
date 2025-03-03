package com.example.eventify.presentation.navigation.entries.events

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.BaseDestination
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedRoute
import javax.inject.Inject


val EventsRootPath = BaseDestination("events")

val EventFeedPath = EventsRootPath.updateAndGetPath("feed")


interface EventsFeedFeatureEntry : ComposableFeatureEntry {
    override val route: String
        get() = EventFeedPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class EventsFeedFeatureEntryImpl @Inject constructor() : EventsFeedFeatureEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        EventsFeedRoute(navController)
    }

}
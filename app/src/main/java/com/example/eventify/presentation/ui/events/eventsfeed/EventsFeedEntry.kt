package com.example.eventify.presentation.ui.events.eventsfeed

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject


val EventsRootPath = BaseDestination("events")

val EventFeedPath = EventsRootPath.child("feed")


interface EventsFeedEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventFeedPath
}

class EventsFeedEntryImpl @Inject constructor() : EventsFeedEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        EventsFeedRoute(navController)
    }

}
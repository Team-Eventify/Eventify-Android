package com.example.eventify.presentation.ui.events.eventdetail

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val EventDetailPath = EventsRootPath.child("{$ARG_EVENT_ID}/detail")

interface EventDetailEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventDetailPath
}

class EventDetailEntryImpl @Inject constructor() : EventDetailEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        EventDetailRoute(navController)
    }

}
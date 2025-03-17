package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val MyEventsPath = EventsRootPath.child("my-events")

interface MyEventsEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = MyEventsPath
}

class MyEventsEntryImpl @Inject constructor() : MyEventsEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        MyEventsRoute(navController)
    }

}
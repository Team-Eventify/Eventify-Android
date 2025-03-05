package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val MyEventsPath = EventsRootPath.updateAndGetPath("my-events")

interface MyEventsEntry : ComposableFeatureEntry {
    override val route: String
        get() = MyEventsPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class MyEventsEntryImpl @Inject constructor() : MyEventsEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        MyEventsRoute(navController)
    }

}
package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val SearchPath = EventsRootPath.child("search")

interface SearchEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SearchPath
}

class SearchEntryImpl @Inject constructor(): SearchEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SearchRoute()
    }

}
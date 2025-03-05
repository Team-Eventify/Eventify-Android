package com.example.eventify.presentation.ui.events.search

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val SearchPath = EventsRootPath.updateAndGetPath("search")

interface SearchEntry : ComposableFeatureEntry {
    override val route: String
        get() = SearchPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class SearchEntryImpl @Inject constructor(): SearchEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SearchRoute()
    }

}
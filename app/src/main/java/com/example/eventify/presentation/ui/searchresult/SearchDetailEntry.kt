package com.example.eventify.presentation.ui.searchresult

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_CATEGORY_ID
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject

val SearchDetailPath = EventsRootPath.child(
    "search-detail",
    ARG_CATEGORY_ID,
)



interface SearchDetailEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SearchDetailPath

}


class SearchDetailEntryImpl @Inject constructor() : SearchDetailEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SearchDetailRoute(navController)
    }

}
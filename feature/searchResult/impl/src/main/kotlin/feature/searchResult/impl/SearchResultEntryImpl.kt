package feature.searchResult.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.searchResult.impl.ui.SearchDetailRoute
import feature.searchResult.api.SearchDetailEntry
import javax.inject.Inject


class SearchDetailEntryImpl @Inject constructor() : SearchDetailEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SearchDetailRoute(navController)
    }

}
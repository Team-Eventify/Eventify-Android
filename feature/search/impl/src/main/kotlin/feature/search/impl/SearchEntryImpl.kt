package feature.search.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.search.api.SearchEntry
import feature.search.impl.ui.SearchRoute
import javax.inject.Inject

class SearchEntryImpl @Inject constructor(): SearchEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SearchRoute(navController)
    }

}
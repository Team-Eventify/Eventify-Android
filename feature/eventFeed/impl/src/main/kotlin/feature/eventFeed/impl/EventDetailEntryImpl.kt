package feature.eventFeed.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.eventFeed.api.EventsFeedEntry
import feature.eventFeed.impl.ui.EventsFeedRoute
import javax.inject.Inject

class EventsFeedEntryImpl @Inject constructor() : EventsFeedEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        EventsFeedRoute(navController)
    }

}
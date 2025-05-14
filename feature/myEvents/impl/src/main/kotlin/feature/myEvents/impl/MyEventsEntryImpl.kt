package feature.myEvents.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.myEvents.api.MyEventsEntry
import feature.myEvents.impl.ui.MyEventsRoute
import javax.inject.Inject


class MyEventsEntryImpl @Inject constructor() : MyEventsEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        MyEventsRoute(navController)
    }

}
package feature.aboutApp.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.aboutApp.api.AboutAppEntry
import feature.aboutApp.impl.ui.AboutAppRoute
import javax.inject.Inject


class AboutAppEntryImpl @Inject constructor(): AboutAppEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        AboutAppRoute(navController)
    }

}
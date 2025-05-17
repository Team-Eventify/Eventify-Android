package feature.decor.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.decor.impl.ui.ProfileDecorRoute
import feature.decor.api.DecorEntry
import javax.inject.Inject

class DecorEntryImpl @Inject constructor(): DecorEntry {

    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileDecorRoute(navController)
    }
}
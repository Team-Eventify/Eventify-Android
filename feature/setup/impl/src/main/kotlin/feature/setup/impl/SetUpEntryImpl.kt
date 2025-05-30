package feature.setup.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.setup.api.SetUpEntry
import feature.setup.impl.ui.SetUpRoute
import javax.inject.Inject

class SetUpEntryImpl @Inject constructor() : SetUpEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SetUpRoute(navController)
    }

}
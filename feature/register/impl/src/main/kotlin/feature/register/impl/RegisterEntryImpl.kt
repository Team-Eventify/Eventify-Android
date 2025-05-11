package feature.register.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.register.api.RegisterEntry
import feature.register.impl.ui.RegisterRoute
import javax.inject.Inject

class RegisterEntryImpl @Inject constructor() : RegisterEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        RegisterRoute(navController)
    }
}
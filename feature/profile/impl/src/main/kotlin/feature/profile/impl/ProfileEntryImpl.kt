package feature.profile.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.profile.api.ProfileEntry
import feature.profile.impl.ui.ProfileRoute
import javax.inject.Inject

class ProfileEntryImpl @Inject constructor(): ProfileEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileRoute(navController)
    }

}
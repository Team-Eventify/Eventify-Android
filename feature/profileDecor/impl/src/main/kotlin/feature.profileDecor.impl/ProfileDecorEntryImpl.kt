package feature.profileDecor.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.profileDecor.api.ProfileDecorEntry
import javax.inject.Inject

class ProfileDecorEntryImpl @Inject constructor(): ProfileDecorEntry {

    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileDecorRoute(navController)
    }
}
package feature.login.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.login.api.LoginEntry
import feature.login.impl.ui.LogInRoute
import javax.inject.Inject


class LoginEntryImpl @Inject constructor() : LoginEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        LogInRoute(navController)
    }

}
package feature.resetPassword.impl

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import feature.resetPassword.api.ResetPasswordEntry
import feature.resetPassword.impl.ui.ResetPasswordRoute
import javax.inject.Inject

class ResetPasswordEntryImpl @Inject constructor() : ResetPasswordEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ResetPasswordRoute(navController)
    }
}
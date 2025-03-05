package com.example.eventify.presentation.ui.account.profile

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject

val AccountRootPath = BaseDestination("account")

val ProfilePath = AccountRootPath.updateAndGetPath("profile")

interface ProfileEntry : ComposableFeatureEntry {
    override val route: String
        get() = ProfilePath

    override val argumentsKeys: List<String>
        get() = emptyList()
}


class ProfileEntryImpl @Inject constructor(): ProfileEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileRoute(navController)
    }

}
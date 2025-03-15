package com.example.eventify.presentation.ui.account.profile_decor

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import javax.inject.Inject

val ProfileDecorPath = AccountRootPath.updateAndGetPath("decor")

interface ProfileDecorEntry: ComposableFeatureEntry {
    override val route: String
        get() = ProfileDecorPath
    override val argumentsKeys: List<String>
        get() = emptyList()
}

class ProfileDecorEntryImpl @Inject constructor(): ProfileDecorEntry {

    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileDecorRoute(navController)
    }
}
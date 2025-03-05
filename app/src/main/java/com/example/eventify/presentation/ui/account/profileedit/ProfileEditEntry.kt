package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import javax.inject.Inject

val ProfileEditPath = AccountRootPath.updateAndGetPath("edit")

interface ProfileEditEntry : ComposableFeatureEntry {
    override val route: String
        get() = ProfileEditPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class ProfileEditEntryImpl @Inject constructor() : ProfileEditEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileEditRoute(navController)
    }

}
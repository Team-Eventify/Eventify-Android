package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import javax.inject.Inject

val ProfileEditPath = AccountRootPath.child("edit")

interface ProfileEditEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfileEditPath
}

class ProfileEditEntryImpl @Inject constructor() : ProfileEditEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileEditRoute(navController)
    }

}
package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import javax.inject.Inject


val SetUpPath = AuthRootPath.child("setup")

interface SetUpEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SetUpPath
}

class SetUpEntryImpl @Inject constructor() : SetUpEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SetUpRoute(navController)
    }

}
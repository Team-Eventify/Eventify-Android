package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import javax.inject.Inject


val SetUpPath = AuthRootPath.updateAndGetPath("setup")

interface SetUpEntry : ComposableFeatureEntry {
    override val route: String
        get() = SetUpPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class SetUpEntryImpl @Inject constructor() : SetUpEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        SetUpRoute(navController)
    }

}
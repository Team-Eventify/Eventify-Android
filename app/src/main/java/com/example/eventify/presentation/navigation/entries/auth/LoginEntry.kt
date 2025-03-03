package com.example.eventify.presentation.navigation.entries.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.BaseDestination
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.LogInRoute
import javax.inject.Inject

val AuthRootPath = BaseDestination("auth")

val LoginPath = AuthRootPath.updateAndGetPath("login")

interface LoginEntry : ComposableFeatureEntry {
    override val route: String
        get() = LoginPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class LoginEntryImpl @Inject constructor() : LoginEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        LogInRoute(navController)
    }

}
package com.example.eventify.presentation.ui.auth.login

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject

val AuthRootPath = BaseDestination("auth")

val LoginPath = AuthRootPath.child("login")

interface LoginEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = LoginPath
}

class LoginEntryImpl @Inject constructor() : LoginEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        LogInRoute(navController)
    }

}
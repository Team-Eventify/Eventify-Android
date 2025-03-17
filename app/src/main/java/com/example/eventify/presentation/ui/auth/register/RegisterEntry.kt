package com.example.eventify.presentation.ui.auth.register

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import javax.inject.Inject

val RegisterPath = AuthRootPath.child("register")

interface RegisterEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = RegisterPath
}

class RegisterEntryImpl @Inject constructor() : RegisterEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        RegisterRoute(navController)
    }
}
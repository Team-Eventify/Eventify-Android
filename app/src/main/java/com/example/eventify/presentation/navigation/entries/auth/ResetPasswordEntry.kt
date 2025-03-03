package com.example.eventify.presentation.navigation.entries.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordRoute
import javax.inject.Inject


val ResetPasswordPath = AuthRootPath.updateAndGetPath("reset_password")

interface ResetPasswordEntry : ComposableFeatureEntry {
    override val route: String
        get() = ResetPasswordPath

    override val argumentsKeys: List<String>
        get() = emptyList()
}

class ResetPasswordEntryImpl @Inject constructor() : ResetPasswordEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ResetPasswordRoute()
    }
}
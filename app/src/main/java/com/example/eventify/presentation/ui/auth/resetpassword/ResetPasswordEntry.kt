package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_SHARED_EMAIL
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import javax.inject.Inject


val ResetPasswordPath = AuthRootPath.child(
    "reset_password",
    ARG_SHARED_EMAIL
)

interface ResetPasswordEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ResetPasswordPath
}

class ResetPasswordEntryImpl @Inject constructor() : ResetPasswordEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ResetPasswordRoute(navController)
    }
}
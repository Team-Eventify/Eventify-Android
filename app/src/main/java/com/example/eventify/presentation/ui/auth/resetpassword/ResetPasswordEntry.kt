package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_SHARED_EMAIL
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import javax.inject.Inject


val ResetPasswordPath = AuthRootPath.updateAndGetPath("reset_password")

interface ResetPasswordEntry : ComposableFeatureEntry {
    override val route: String
        get() = ResetPasswordPath

    override val argumentsKeys: List<String>
        get() = listOf(
            ARG_SHARED_EMAIL,
        )
}

class ResetPasswordEntryImpl @Inject constructor() : ResetPasswordEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        ResetPasswordRoute(navController)
    }
}
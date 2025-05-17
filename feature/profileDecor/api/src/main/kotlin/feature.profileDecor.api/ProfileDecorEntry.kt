package com.example.eventify.presentation.ui.account.profile_decor

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry
import javax.inject.Inject
import core.common.navigation.AccountRoot


val ProfileDecorPath = AccountRoot.child("decor")

interface ProfileDecorEntry: ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfileDecorPath
}

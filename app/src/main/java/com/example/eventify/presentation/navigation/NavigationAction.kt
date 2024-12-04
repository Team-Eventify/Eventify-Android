package com.example.eventify.presentation.navigation

import androidx.navigation.NavOptionsBuilder
import com.example.eventify.presentation.navigation.navgraphs.Destination

sealed interface NavigationAction {

    data class Navigate(
        val destination: Destination,
        val navOptions: NavOptionsBuilder.() -> Unit = {}
    ): NavigationAction

    data object NavigateUp: NavigationAction
}
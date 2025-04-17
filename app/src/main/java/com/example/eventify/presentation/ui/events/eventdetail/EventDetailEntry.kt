package com.example.eventify.presentation.ui.events.eventdetail

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.ARG_EVENT_ID
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import javax.inject.Inject

val EventDetailPath = EventsRootPath.child("{$ARG_EVENT_ID}/detail")

interface EventDetailEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = EventDetailPath
}

class EventDetailEntryImpl @Inject constructor() : EventDetailEntry {
    @Composable
    override fun Composable(navController: NavHostController) {
        EventDetailRoute(navController)
    }

    override val enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition?)?
        get() = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Start,
                tween(300)
            )
        }


    override val popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition?)?
        get() = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.End,
                tween(300)
            )
        }

}
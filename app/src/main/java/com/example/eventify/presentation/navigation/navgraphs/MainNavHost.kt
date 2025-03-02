package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingRoute
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailRoute
import com.example.eventify.presentation.ui.events.feedback.FeedbackRoute
import com.example.eventify.presentation.ui.events.feedback.FeedbackScreen
import kotlinx.serialization.Serializable


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: Any = RootRouter.AuthRoute,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.then(modifier)
    ){
        HomeNavGraph(navController = navController)
        AuthNavGraph()
        SettingsNavGraph()
        
        composable<RootRouter.EventDetailRoute>{
            EventDetailRoute(
                navController = navController,
            )
        }

        composable<RootRouter.EventFeedbackRoute> {
            FeedbackRoute()
        }
        composable<RootRouter.OnboardingRoute> {
            OnBoardingRoute()
        }
    }
}

sealed class RootRouter: Destination {
    @Serializable
    data object HomeRoute : RootRouter()

    @Serializable
    data object AuthRoute : RootRouter()
    
    @Serializable
    data class EventDetailRoute(val eventId: String) : RootRouter()

    @Serializable
    data class EventFeedbackRoute(val eventId: String) : RootRouter()

    @Serializable
    data object SettingsRoute : RootRouter()

    @Serializable
    data object OnboardingRoute : AuthRouter()

}
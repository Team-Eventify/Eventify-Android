package com.example.eventify.presentation.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.entries.BaseDestination
import com.example.eventify.presentation.navigation.entries.ComposableFeatureEntry
import com.example.eventify.presentation.navigation.entries.auth.AuthRootPath
import com.example.eventify.presentation.navigation.entries.auth.LoginEntry
import com.example.eventify.presentation.navigation.entries.auth.LoginPath
import com.example.eventify.presentation.navigation.entries.auth.OnBoardingEntry
import com.example.eventify.presentation.navigation.entries.auth.RegisterEntry
import com.example.eventify.presentation.navigation.entries.auth.RegisterPath
import com.example.eventify.presentation.navigation.entries.auth.ResetPasswordEntry
import com.example.eventify.presentation.navigation.entries.events.EventDetailEntry
import com.example.eventify.presentation.navigation.entries.events.EventsFeedFeatureEntry
import com.example.eventify.presentation.navigation.entries.events.MyEventsEntry
import com.example.eventify.presentation.navigation.entries.events.SearchEntry
import com.example.eventify.presentation.navigation.findOrNull
import kotlinx.serialization.Serializable



@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: Any = RootRouter.AuthRoute,
    modifier: Modifier = Modifier
) {
    val features = LocalFeaturesProvider.current.features

    val authFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<LoginEntry>(),
        features.findOrNull<RegisterEntry>(),
        features.findOrNull<ResetPasswordEntry>(),
        features.findOrNull<OnBoardingEntry>(),
    )

    val eventsFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<EventsFeedFeatureEntry>(),
        features.findOrNull<EventDetailEntry>(),
        features.findOrNull<MyEventsEntry>(),
        features.findOrNull<SearchEntry>(),
    )

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = AuthRootPath.baseRoute
    ){

        addAuthNavGraph(navController, authFeatures)
        addEventsNavGraph(navController, eventsFeatures)

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
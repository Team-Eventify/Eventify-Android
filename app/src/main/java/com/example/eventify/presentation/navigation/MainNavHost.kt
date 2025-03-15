package com.example.eventify.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.eventify.presentation.ui.account.profileedit.ProfileEditEntry
import com.example.eventify.presentation.ui.account.profile.ProfileEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import com.example.eventify.presentation.ui.auth.login.LoginEntry
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingEntry
import com.example.eventify.presentation.ui.auth.register.RegisterEntry
import com.example.eventify.presentation.ui.auth.resetpassword.ResetPasswordEntry
import com.example.eventify.presentation.ui.events.eventdetail.EventDetailEntry
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntry
import com.example.eventify.presentation.ui.events.myevents.MyEventsEntry
import com.example.eventify.presentation.ui.events.search.SearchEntry
import com.example.eventify.presentation.ui.settings.aboutapp.AboutAppEntry
import com.example.eventify.presentation.navigation.navgraphs.addAccountNavGraph
import com.example.eventify.presentation.navigation.navgraphs.addAuthNavGraph
import com.example.eventify.presentation.navigation.navgraphs.addEventsNavGraph
import com.example.eventify.presentation.navigation.navgraphs.addSettingsNavGraph
import com.example.eventify.presentation.ui.account.profile_decor.ProfileDecorEntry
import com.example.eventify.presentation.ui.auth.choosecategories.SetUpEntry


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String = AuthRootPath.baseRoute,
    modifier: Modifier = Modifier
) {
    val features = LocalFeaturesProvider.current.features

    val authFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<LoginEntry>(),
        features.findOrNull<RegisterEntry>(),
        features.findOrNull<ResetPasswordEntry>(),
        features.findOrNull<SetUpEntry>(),
    )

    val eventsFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<EventsFeedEntry>(),
        features.findOrNull<EventDetailEntry>(),
        features.findOrNull<MyEventsEntry>(),
        features.findOrNull<SearchEntry>(),
    )

    val settingsFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<AboutAppEntry>(),
    )

    val accountFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.findOrNull<ProfileEntry>(),
        features.findOrNull<ProfileEditEntry>(),
        features.findOrNull<ProfileDecorEntry>(),
    )

    val onboarding = features.find<OnBoardingEntry>()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        with(onboarding) {
            featureComposable(navController)
        }

        addAuthNavGraph(navController, authFeatures)
        addEventsNavGraph(navController, eventsFeatures)
        addSettingsNavGraph(navController, settingsFeatures)
        addAccountNavGraph(navController, accountFeatures)


    }
}

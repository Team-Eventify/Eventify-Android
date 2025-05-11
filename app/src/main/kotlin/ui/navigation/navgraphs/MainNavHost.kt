package ui.navigation.navgraphs

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import core.common.navigation.ComposableFeatureEntry
import core.featureManager.LocalFeaturesProvider
import core.featureManager.find
import feature.aboutApp.api.AboutAppEntry
import feature.aboutApp.api.ResetPasswordEntry
import feature.eventDetail.api.EventDetailEntry
import feature.eventFeed.api.EventsFeedEntry
import feature.login.api.LoginEntry
import feature.myEvents.api.MyEventsEntry
import feature.onboarding.api.OnBoardingEntry
import feature.profile.api.ProfileEntry
import feature.profileEdit.api.ProfileEditEntry
import feature.register.api.RegisterEntry


@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String = AuthRootPath.baseRoute,
    modifier: Modifier = Modifier
) {
    val features = LocalFeaturesProvider.current.features

    val authFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.find<LoginEntry>(),
        features.find<RegisterEntry>(),
        features.find<ResetPasswordEntry>(),
//        features.find<SetUpEntry>(),
    )

    val eventsFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.find<EventsFeedEntry>(),
        features.find<EventDetailEntry>(),
        features.find<MyEventsEntry>(),
//        features.find<SearchEntry>(),
//        features.find<SearchDetailEntry>()
    )

    val settingsFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.find<AboutAppEntry>(),
    )

    val accountFeatures: List<ComposableFeatureEntry> = listOfNotNull(
        features.find<ProfileEntry>(),
        features.find<ProfileEditEntry>(),
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

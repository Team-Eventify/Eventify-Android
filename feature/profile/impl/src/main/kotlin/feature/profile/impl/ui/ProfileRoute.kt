package feature.profile.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import core.common.extentions.redirect
import feature.aboutApp.api.AboutAppEntry
import feature.profile.api.ProfileListener
import feature.profile.impl.ProfileViewModel
import feature.profile.impl.components.LoadingProfile
import feature.profile.impl.state.UiState
import feature.profileEdit.api.ProfileEditEntry
import uikit.components.screens.ErrorScreen
import uikit.components.topBar.LocalTopBarState
import core.featureManager.LocalFeaturesProvider
import core.featureManager.navigateToFeature
import feature.decor.api.DecorEntry
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import com.example.eventify.uikit.R as UiKitR
import com.example.eventify.core.common.R as CommonR


@Composable
internal fun ProfileRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val uiState: UiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features


    val listener = object : ProfileListener {
        override fun onLogOut() {
            viewModel.logOut()
        }

        override fun navigateToProfileEdit() {
            features.navigateToFeature<ProfileEditEntry>(navController)
        }

        override fun navigateToAppInfo() {
            features.navigateToFeature<AboutAppEntry>(navController)
        }

        override fun navigateToDecor() {
            features.navigateToFeature<DecorEntry>(navController)
        }

        override fun navigateToSupport() {
            "https://t.me/idoverchiviiloh".redirect(context)
        }
    }

    when (uiState) {
        UiState.Error -> {
            ErrorScreen(
                title = stringResource(UiKitR.string.failed_load_profile),
                description = stringResource(CommonR.string.try_again_later)
            )
        }
        UiState.Loading -> LoadingProfile()
        is UiState.ShowProfile -> {
            ProfileScreen((uiState as UiState.ShowProfile), listener)
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(UiKitR.string.profile),
                size = TopBarSize.SMALL,
            )
        )
    }

}

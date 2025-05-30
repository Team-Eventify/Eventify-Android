package feature.aboutApp.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.uikit.R
import core.common.extentions.redirect
import feature.aboutApp.impl.state.AboutAppListener
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarAction
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState


@Composable
fun AboutAppRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<AboutAppViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    val listener = object : AboutAppListener {
        override fun navigateUp() {
            navController.popBackStack()
        }

        override fun goToAboutUs() {
            "https://t.me/EventifyApp".redirect(context)
        }

        override fun goPrivacyPolicy() {
        }

        override fun goTermsOfUse() {
        }

        override fun goToInformationSecurity() {
        }

        override fun goToDonate() {
        }

    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.about_app),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::navigateUp
                )
            )
        )
    }

    AboutAppScreen(uiState, listener)
}


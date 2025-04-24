package com.example.eventify.presentation.ui.settings.aboutapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.settings.aboutapp.state.AboutAppListener


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


package com.example.eventify.presentation.ui.account.aboutapp

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
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.account.aboutapp.state.AboutAppListener
import com.example.eventify.presentation.ui.common.DefaultTopAppBar


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
            navController.navigateUp()
        }

        override fun goToAboutUs() {
            TODO("Not yet implemented")
        }

        override fun goPrivacyPolicy() {
            TODO("Not yet implemented")
        }

        override fun goTermsOfUse() {
            TODO("Not yet implemented")
        }

        override fun goToInformationSecurity() {
            TODO("Not yet implemented")
        }

        override fun goToDonate() {
            TODO("Not yet implemented")
        }

    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.about_app),
                size = TopBarSize.SMALL,
            )
        )
    }

    AboutAppScreen(uiState, listener)
}


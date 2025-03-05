package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.ui.auth.onboarding.state.OnBoardingListener
import com.example.eventify.presentation.ui.auth.onboarding.state.SideEffect
import com.example.eventify.presentation.ui.auth.register.RegisterEntry
import com.example.eventify.presentation.utils.ObserveAsEvent


@Composable
fun OnBoardingRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<OnBoardingViewModel>()
    val state by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : OnBoardingListener {
        override fun nextPage() {
            viewModel.nextPage()
        }

        override fun previousPage() {
            viewModel.goToPreviousPage()
        }

        override fun goToPage(pageIndex: Int) {
            viewModel.goToPage(pageIndex)
        }

        override fun finish() {
            viewModel.finishOnboarding()
        }


    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.FinishOnBoarding -> {
                features.navigateNewTaskFeature<RegisterEntry, OnBoardingEntry>(navController)
            }
        }
    }

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    OnBoardingScreen(state, listener)
}


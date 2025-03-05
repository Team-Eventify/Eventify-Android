package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.clearNavigate
import com.example.eventify.presentation.navigation.navigateNewTaskFeature
import com.example.eventify.presentation.ui.auth.choosecategories.state.SetUpListener
import com.example.eventify.presentation.ui.auth.choosecategories.state.SideEffect
import com.example.eventify.presentation.ui.events.eventsfeed.EventsFeedEntry
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun SetUpRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ChooseCategoriesViewModel>()
    val uiState by viewModel.stateFlow.collectAsState()
    val features = LocalFeaturesProvider.current.features
    val snackBarState = LocaleSnackbarState.current

    val listener = object : SetUpListener {
        override fun selectCategory(categoryId: String, selected: Boolean) {
            viewModel.changeCategoryFilterActive(categoryId, selected)
        }

        override fun skip() {
            features.navigateNewTaskFeature<EventsFeedEntry, SetUpEntry>(navController)
        }

        override fun nextStep() {
            viewModel.setCategories()
        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is SideEffect.FailedProvideCategories -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: ""
                )
            }
            SideEffect.FinishSetUp -> {
                features.clearNavigate<EventsFeedEntry>(navController)
            }
        }
    }

    ChooseCategoriesScreen(uiState, listener)
}

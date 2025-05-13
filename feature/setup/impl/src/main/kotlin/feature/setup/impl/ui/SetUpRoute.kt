package feature.setup.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import feature.setup.impl.components.ChooseCategories
import feature.setup.impl.components.SetupPersonData
import feature.setup.impl.state.SetUpListener
import feature.setup.impl.state.SetupStep
import feature.setup.impl.state.SideEffect
import core.featureManager.LocalFeaturesProvider
import core.featureManager.clearNavigate
import feature.eventFeed.api.EventsFeedEntry
import uikit.LocaleSnackbarState
import uikit.utils.ObserveAsEvent

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

        override fun onChangeFirstName(value: String) {
            viewModel.changeFirstName(value)
        }

        override fun onChangeLastName(value: String) {
            viewModel.changeLastName(value)
        }

        override fun flowNext() {
            viewModel.flowNextStep()
        }

        override fun flowBack() {
            viewModel.flowPreviousStep()
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

    when (uiState.currentStep) {
        SetupStep.Initial -> {}
        SetupStep.ChooseCategories -> ChooseCategories(uiState, listener)
        SetupStep.SetUserData -> SetupPersonData(uiState, listener)
    }
}

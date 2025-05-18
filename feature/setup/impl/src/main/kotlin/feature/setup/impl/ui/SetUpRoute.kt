package feature.setup.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
import uikit.LocalSnackbarState
import uikit.components.snackbar.SnackbarStyle
import uikit.utils.ObserveAsEvent
import com.example.eventify.core.common.R as CommonR
import com.example.eventify.uikit.R as UiKitR


@Composable
fun SetUpRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ChooseCategoriesViewModel>()
    val uiState by viewModel.stateFlow.collectAsState()
    val features = LocalFeaturesProvider.current.features
    val snackBarState = LocalSnackbarState.current
    val context = LocalContext.current

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
                snackBarState.show(
                    message = context.getString(UiKitR.string.server_error),
                    description = context.getString(CommonR.string.try_again),
                    style = SnackbarStyle.Error,
                )
            }
            SideEffect.FinishSetUp -> {
                features.clearNavigate<EventsFeedEntry>(navController)
            }

            SideEffect.FailedSetUserData -> {
                snackBarState.show(
                    message = "Не удалось обноыить данные",
                    description = context.getString(CommonR.string.try_again),
                    style = SnackbarStyle.Error
                )
            }
        }
    }

    when (uiState.currentStep) {
        SetupStep.Initial -> {}
        SetupStep.ChooseCategories -> ChooseCategories(uiState, listener)
        SetupStep.SetUserData -> SetupPersonData(uiState, listener)
    }
}

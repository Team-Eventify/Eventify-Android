package feature.eventDetail.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import feature.eventDetail.api.EventDetailListener
import feature.eventDetail.impl.EventDetailViewModel
import feature.eventDetail.impl.ui.components.LoadingEvent
import feature.eventDetail.impl.state.EventDetailUiState
import feature.eventDetail.impl.state.SideEffect
import uikit.LocalSnackbarState
import uikit.components.screens.ErrorScreen
import uikit.components.snackbar.SnackbarType
import uikit.utils.ObserveAsEvent
import com.example.eventify.uikit.R as UiKitR


@Composable
fun EventDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<EventDetailViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val snackBarState = LocalSnackbarState.current

    val listener = object : EventDetailListener {
        override fun navigateUp() {
            navController.navigateUp()
        }

        override fun onActionClick() {
            viewModel.primaryAction()
        }

        override fun goToRatePage() {
            TODO("Not yet implemented")
        }

        override fun refresh() {
            viewModel.refresh()
        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            SideEffect.SuccessSubscribeEvent -> {
                viewModel.refresh()
                snackBarState.show(
                    message = "Вы подписались на событие",
                    type = SnackbarType.Success,
                )
            }
            SideEffect.SuccessUnsubscribeEvent -> {
                snackBarState.show(
                    message = "Вы отписались от события",
                    type = SnackbarType.Success,
                )
            }
            is SideEffect.FailSubscribeEvent -> {
                snackBarState.show(
                    message = sideEffect.message ?: "Не удалось подписаться на событие",
                    type = SnackbarType.Error,
                )
            }
            is SideEffect.FailUnsubscribeEvent -> {
                snackBarState.show(
                    message = sideEffect.message ?: "Не удалось отписаться от события",
                    type = SnackbarType.Error,
                )
            }
        }
    }

    when (uiState) {
        EventDetailUiState.Loading -> LoadingEvent()

        is EventDetailUiState.Error -> ErrorScreen(
            title = stringResource(UiKitR.string.error_load_event),
            description = (uiState as EventDetailUiState.Error).message
        )
        is EventDetailUiState.ShowEvent -> {
            EventDetailScreen(uiState as EventDetailUiState.ShowEvent, listener)
        }
    }
}


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
import uikit.components.snackbar.SnackbarStyle
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
                snackBarState.show(
                    message = "Ты успешно записался на событие!",
                    description = "Добавлено в твои события",
                    force = true,
                    style = SnackbarStyle.Success(
                        iconResId = UiKitR.drawable.check_circle_fill,
                    ),
                )
            }
            SideEffect.SuccessUnsubscribeEvent -> {
                snackBarState.show(
                    message = "Ты отписался от события!",
                    description = "Участие отменено",
                    force = true,
                    style = SnackbarStyle.Success(
                        iconResId = UiKitR.drawable.xmark_circle_fill,
                    ),
                )
            }
            is SideEffect.FailSubscribeEvent -> {
                snackBarState.show(
                    message = "Не удалось подписаться на событие",
                    description = "Попробуйте еще раз позже",
                    style = SnackbarStyle.Error,
                )
            }
            is SideEffect.FailUnsubscribeEvent -> {
                snackBarState.show(
                    message = "Не удалось отписаться от события",
                    description = "Попробуйте еще раз позже",
                    style = SnackbarStyle.Error,
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


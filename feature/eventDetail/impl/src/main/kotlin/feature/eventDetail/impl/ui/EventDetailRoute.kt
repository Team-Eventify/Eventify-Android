package feature.eventDetail.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
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
import com.example.eventify.feature.eventDetail.impl.R as DetailR
import com.example.eventify.core.common.R as CommonR


@Composable
fun EventDetailRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<EventDetailViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val snackBarState = LocalSnackbarState.current
    val context = LocalContext.current

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
                    message = context.getString(DetailR.string.successful_subscribe_title),
                    description = context.getString(DetailR.string.successful_subscribe_description),
                    force = true,
                    style = SnackbarStyle.Success(
                        iconResId = UiKitR.drawable.check_circle_fill,
                    ),
                )
            }
            SideEffect.SuccessUnsubscribeEvent -> {
                snackBarState.show(
                    message = context.getString(DetailR.string.successful_unsubscribe_title),
                    description = context.getString(DetailR.string.successful_unsubscribe_description),
                    force = true,
                    style = SnackbarStyle.Success(
                        iconResId = UiKitR.drawable.xmark_circle_fill,
                    ),
                )
            }
            is SideEffect.FailSubscribeEvent -> {
                snackBarState.show(
                    message = context.getString(DetailR.string.failed_subscribe),
                    description = context.getString(CommonR.string.try_again),
                    style = SnackbarStyle.Error,
                )
            }
            is SideEffect.FailUnsubscribeEvent -> {
                snackBarState.show(
                    message = context.getString(DetailR.string.failed_unsubscribe),
                    description = context.getString(CommonR.string.try_again),
                    style = SnackbarStyle.Error,
                )
            }
        }
    }

    when (uiState) {
        EventDetailUiState.Loading -> LoadingEvent()

        EventDetailUiState.Error -> ErrorScreen(
            title = stringResource(UiKitR.string.error_load_event),
            description = stringResource(CommonR.string.try_again_later)
        )
        is EventDetailUiState.ShowEvent -> {
            EventDetailScreen(uiState as EventDetailUiState.ShowEvent, listener)
        }
    }
}


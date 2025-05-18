package feature.decor.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import core.common.theme.ThemeType
import feature.decor.impl.state.DecorListener
import feature.decor.impl.state.SideEffect
import uikit.components.topBar.LocalTopBarState
import uikit.LocalSnackbarState
import uikit.components.snackbar.SnackbarStyle
import uikit.components.topBar.TopBarAction
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import uikit.utils.ObserveAsEvent
import com.example.eventify.uikit.R as UiKitR

@Composable
fun ProfileDecorRoute(navController: NavController) {
    val viewModel = hiltViewModel<DecorViewModel>()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val snackBarState = LocalSnackbarState.current
    val uiState by viewModel.state.collectAsStateWithLifecycle()

    val listener = object : DecorListener {
        override fun onChangeTheme(theme: ThemeType) {
            viewModel.changeTheme(theme)
        }

        override fun navigateBack() {
            navController.navigateUp()
        }

    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is SideEffect.FailUpdate -> {
                snackBarState.show(
                    message = sideEffect.message ?: "",
                    style = SnackbarStyle.Error,
                )
            }

            SideEffect.SuccessUpdate -> {
                snackBarState.show(
                    message = context.getString(UiKitR.string.icon_logo_update),
                    style = SnackbarStyle.Success(),
                )
            }
        }
    }

    ProfileDecorScreen(uiState, listener)

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(UiKitR.string.design_section_title),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = UiKitR.drawable.ic_chevron_right,
                    onClick = listener::navigateBack
                )
            )
        )
    }
}
package com.example.eventify.presentation.ui.account.profile_decor

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.eventify.R
import com.example.eventify.presentation.LocalAppThemeState
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.models.LogoIcon
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorUiState
import com.example.eventify.presentation.ui.account.profile_decor.state.SideEffect
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
import com.example.eventify.presentation.utils.ObserveAsEvent

@Composable
fun ProfileDecorRoute(navController: NavController) {
    val viewModel = hiltViewModel<ProfileDecorViewModel>()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val appThemeManager = LocalAppThemeState.current
    val snackBarState = LocaleSnackbarState.current

    val uiState = ProfileDecorUiState(
        isSystemOrDarkTheme = appThemeManager.getTypeTheme() == TypesTheme.DARK_THEME
            || (appThemeManager.getTypeTheme() == TypesTheme.SYSTEM_THEME && isSystemInDarkTheme()),
        activeTypeOfTheme = appThemeManager.isDarkTheme.value)

    val listener = object : ProfileDecorRouteListener {
        override fun onBackClick() {
            navController.navigateUp()
        }
        override fun changeTheme(typeOfTheme: TypesTheme) {
            appThemeManager.changeTheme(typeOfTheme)
        }
        override fun getActiveTheme(): TypesTheme {
            return appThemeManager.getTypeTheme()
        }

        override fun changeIcon(icon: LogoIcon) {
            viewModel.updateIcon(icon)
        }
    }

    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
        when (sideEffect) {
            is SideEffect.FailUpdate -> {
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: ""
                )
            }

            SideEffect.SuccessUpdate -> {
                snackBarState.showSnackbar(
                    message = context.getString(R.string.icon_logo_update)
                )
            }
            else -> {}
        }
    }

    ProfileDecorScreen(uiState, listener)

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.design_section_title),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
}
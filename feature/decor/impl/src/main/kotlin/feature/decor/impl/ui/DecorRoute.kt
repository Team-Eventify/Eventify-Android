package feature.decor.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import uikit.components.topBar.LocalTopBarState
import uikit.LocalSnackbarState

@Composable
fun ProfileDecorRoute(navController: NavController) {
    val viewModel = hiltViewModel<DecorViewModel>()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
//    val appThemeManager = LocalAppThemeState.current
    val snackBarState = LocalSnackbarState.current

//    val uiState = ProfileDecorUiState(
//        isSystemOrDarkTheme = appThemeManager.getTypeTheme() == TypesTheme.DARK_THEME
//            || (appThemeManager.getTypeTheme() == TypesTheme.SYSTEM_THEME && isSystemInDarkTheme()),
//        activeTypeOfTheme = appThemeManager.isDarkTheme.value,
//        activeTypeOfIconAlias = viewModel.getPreviousAlias())
//
//    val listener = object : ProfileDecorRouteListener {
//        override fun onBackClick() {
//            navController.navigateUp()
//        }
//        override fun changeTheme(typeOfTheme: TypesTheme) {
//            appThemeManager.changeTheme(typeOfTheme)
//        }
//        override fun getActiveTheme(): TypesTheme {
//            return appThemeManager.getTypeTheme()
//        }
//
//        override fun changeIcon(icon: LogoIcon) {
//            viewModel.updateIcon(icon)
//        }
//    }
//
//    ObserveAsEvent(viewModel.sideEffect) { sideEffect ->
//        when (sideEffect) {
//            is SideEffect.FailUpdate -> {
//                snackBarState.showSnackbar(
//                    message = sideEffect.message ?: ""
//                )
//            }
//
//            SideEffect.SuccessUpdate -> {
//                snackBarState.showSnackbar(
//                    message = context.getString(UiKitR.string.icon_logo_update)
//                )
//            }
//        }
//    }
//
//    ProfileDecorScreen(uiState, listener)
//
//    LaunchedEffect(Unit) {
//        topBarState.setUp(
//            TopBarState.Base(
//                title = context.getString(UiKitR.string.design_section_title),
//                size = TopBarSize.SMALL,
//                leftAction = TopBarAction(
//                    iconRes = UiKitR.drawable.ic_chevron_right,
//                    onClick = listener::onBackClick
//                )
//            )
//        )
//    }
}
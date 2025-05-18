package feature.profileEdit.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.uikit.R
import uikit.components.screens.ErrorScreen
import feature.profileEdit.api.ProfileEditListener
import feature.profileEdit.impl.ProfileEditViewModel
import feature.profileEdit.impl.components.LoadingProfileEdit
import feature.profileEdit.impl.state.SideEffect
import feature.profileEdit.impl.state.UiState
import uikit.LocalSnackbarState
import uikit.components.snackbar.SnackbarStyle
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.TopBarAction
import uikit.components.topBar.TopBarSize
import uikit.components.topBar.TopBarState
import uikit.utils.ObserveAsEvent

@Composable
fun ProfileEditRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<ProfileEditViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val snackBarState = LocalSnackbarState.current

    val listener = object : ProfileEditListener {
        override fun onSubmit() {
            viewModel.saveUser()
        }

        override fun onChangeCategoryFilterActive(categoryId: String, value: Boolean) {
            viewModel.changeCategoryFilterActive(categoryId, value)
        }

        override fun onChangeEmail(email: String) {
            viewModel.changeUserEmail(email)
        }

        override fun onChangeFirstName(firstName: String) {
            viewModel.changeUserFirstName(firstName)
        }

        override fun onChangeLastName(lastName: String) {
            viewModel.changeUserLastName(lastName)
        }

        override fun onChangeTelegram(telegram: String) {
            viewModel.changeUserTelegram(telegram)
        }

        override fun onDeleteAccount() {
            viewModel.deleteAccount()
        }

        override fun onBackClick() {
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
                    message = context.getString(R.string.user_updated),
                    description = "Изменения сохранены",
                    style = SnackbarStyle.Success(),
                )
            }

            SideEffect.AccountDeleted -> {
                snackBarState.show(
                    message = "Аккаунт удален",
                    style = SnackbarStyle.Success(),
                )
            }

            SideEffect.FailedDeleteAccount -> {
                snackBarState.show(
                    message = "Не удалось удалить аккаунт",
                    description = "Попробуйте позже",
                    style = SnackbarStyle.Error,
                )
            }
        }
    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.profile_edit_title),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
    when (uiState) {
        UiState.Loading -> LoadingProfileEdit()
        UiState.Error -> ErrorScreen(
            title = stringResource(R.string.failed_load_profile)
        )
        is UiState.ShowProfileEdit -> {
            ProfileEditScreen(uiState as UiState.ShowProfileEdit, listener)
        }
    }

}
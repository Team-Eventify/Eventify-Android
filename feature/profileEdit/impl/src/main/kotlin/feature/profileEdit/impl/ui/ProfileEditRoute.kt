package feature.profileEdit.impl.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.account.profileedit.components.LoadingProfileEdit
import com.example.eventify.presentation.ui.account.profileedit.state.ProfileEditListener
import com.example.eventify.presentation.ui.account.profileedit.state.SideEffect
import com.example.eventify.presentation.ui.account.profileedit.state.UiState
import com.example.eventify.presentation.ui.common.screens.ErrorScreen
import com.example.eventify.presentation.utils.ObserveAsEvent
import feature.profileEdit.api.ProfileEditListener
import feature.profileEdit.impl.ProfileEditViewModel
import feature.profileEdit.impl.components.LoadingProfileEdit
import feature.profileEdit.impl.state.SideEffect
import feature.profileEdit.impl.state.UiState
import uikit.LocaleSnackbarState
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
    val snackBarState = LocaleSnackbarState.current

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
                snackBarState.showSnackbar(
                    message = sideEffect.message ?: ""
                )
            }
            SideEffect.SuccessUpdate -> {
                snackBarState.showSnackbar(
                    message = context.getString(R.string.user_updated)
                )
            }

            SideEffect.AccountDeleted -> {
                snackBarState.showSnackbar(
                    message = "Аккаунт удален"
                )
            }

            SideEffect.FailedDeleteAccount -> {
                snackBarState.showSnackbar(
                    message = "Не удалось удалить аккаунт"
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
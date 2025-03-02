package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar

@Composable
fun ProfileEditRoute(
    coordinator: ProfileEditCoordinator = rememberProfileEditCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val actions = rememberProfileEditActions(coordinator)
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.profile_edit_title),
                size = TopBarSize.SMALL,
            )
        )
    }

    ProfileEditScreen(uiState, actions)

}


@Composable
fun rememberProfileEditActions(coordinator: ProfileEditCoordinator): ProfileEditActions {
    return remember(coordinator) {
        ProfileEditActions(
            onSubmit = coordinator.viewModel::saveUser,
            onChangeEmail = coordinator.viewModel::changeUserEmail,
            onChangeFirstName = coordinator.viewModel::changeUserFirstName,
            onChangeLastName = coordinator.viewModel::changeUserLastName,
            onChangeTelegram = coordinator.viewModel::changeUserTelegram,
            onChangeCategoryFilterActive = coordinator.viewModel::changeCategoryFilterActive,
            onDeleteAccount = coordinator.viewModel::deleteAccount,
        )
    }
}
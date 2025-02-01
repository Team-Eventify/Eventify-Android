package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.R
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar

@Composable
fun ProfileEditRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: ProfileEditCoordinator = rememberProfileEditCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberProfileEditActions(coordinator)

    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = false,
            topBar = {
                DefaultTopAppBar(
                    title = stringResource(R.string.profile_edit_title),
                    onNavigateUp = coordinator::navigateBack
                )
            }
        )
    }

    // UI Rendering
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
            onChangeMiddleName = coordinator.viewModel::changeUserMiddleName,
            onChangeTelegram = coordinator.viewModel::changeUserTelegram,
            onChangeCategoryFilterActive = coordinator.viewModel::changeCategoryFilterActive,
            onDeleteAccount = coordinator.viewModel::deleteAccount,
        )
    }
}
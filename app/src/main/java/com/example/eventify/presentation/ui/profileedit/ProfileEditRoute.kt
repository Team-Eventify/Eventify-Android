package com.example.eventify.presentation.ui.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ProfileEditRoute(
    coordinator: ProfileEditCoordinator = rememberProfileEditCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    val currentUserState by coordinator.viewModel.currentUser.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberProfileEditActions(coordinator)

    // UI Rendering
    if (currentUserState != null){
        ProfileEditScreen(uiState, actions)
    }
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
            onToggleCategoryItem = coordinator.viewModel::toggleCategorySelection
        )
    }
}
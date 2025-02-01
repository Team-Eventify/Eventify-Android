package com.example.eventify.presentation.ui.account.profile

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
fun ProfileRoute(
    scaffoldViewState: MutableState<ScaffoldViewState>,
    coordinator: ProfileCoordinator = rememberProfileCoordinator()
) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()

    // UI Actions
    val actions = rememberProfileActions(coordinator)
    
    LaunchedEffect(Unit) {
        scaffoldViewState.value = scaffoldViewState.value.copy(
            showBottomBar = true,
            topBar = {
                DefaultTopAppBar(title = stringResource(R.string.profile))
            }
        )
    }

    LaunchedEffect(Unit) {
        coordinator.viewModel.loadData()
    }

    // UI Rendering
    ProfileScreen(uiState, actions)
}


@Composable
fun rememberProfileActions(coordinator: ProfileCoordinator): ProfileActions {
    return remember(coordinator) {
        ProfileActions(
            onLogOut = coordinator.viewModel::logOut,
            navigateToProfileEdit = coordinator.viewModel::navigateToEditProfile,
            navigateToAppInfo = coordinator.viewModel::navigateToAppInfo
        )
    }
}
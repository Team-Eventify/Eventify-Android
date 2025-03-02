package com.example.eventify.presentation.ui.account.profile

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
fun ProfileRoute(
    coordinator: ProfileCoordinator = rememberProfileCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsStateWithLifecycle()
    val actions = rememberProfileActions(coordinator)
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.profile),
                size = TopBarSize.SMALL,
            )
        )
    }

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
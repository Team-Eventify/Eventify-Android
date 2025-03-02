package com.example.eventify.presentation.ui.auth.choosecategories

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember

@Composable
fun ChooseCategoriesRoute(
    coordinator: ChooseCategoriesCoordinator = rememberChooseCategoriesCoordinator()
) {
    val uiState by coordinator.screenStateFlow.collectAsState(ChooseCategoriesState())
    val actions = rememberChooseCategoriesActions(coordinator)

    ChooseCategoriesScreen(uiState, actions)
}


@Composable
fun rememberChooseCategoriesActions(coordinator: ChooseCategoriesCoordinator): ChooseCategoriesActions {
    return remember(coordinator) {
        ChooseCategoriesActions(
            onSkip = coordinator.viewModel::skipStep,
            onSubmit = coordinator.viewModel::setCategories,
            onChangeCategoryFilterActive = coordinator.viewModel::changeCategoryFilterActive
        )
    }
}
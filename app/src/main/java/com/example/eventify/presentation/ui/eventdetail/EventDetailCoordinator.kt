package com.example.eventify.presentation.ui.eventdetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class EventDetailCoordinator(
    private val navController: NavHostController,
    val viewModel: EventDetailViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun navigateUp(){
        navController.navigateUp()
    }
}

@Composable
fun rememberEventDetailCoordinator(
    navController: NavHostController,
    viewModel: EventDetailViewModel = hiltViewModel()
): EventDetailCoordinator {
    return remember(viewModel) {
        EventDetailCoordinator(
            navController = navController,
            viewModel = viewModel
        )
    }
}
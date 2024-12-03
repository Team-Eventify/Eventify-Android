package com.example.eventify.presentation.ui.eventsfeed

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class EventsFeedCoordinator(
    val viewModel: EventsFeedViewModel,
    val navController: NavHostController
) {
    val screenStateFlow = viewModel.stateFlow

//    fun navigateToEventDetail(eventId: String) = navController.navigate(RootRouter(eventId))
    fun navigateToEventDetail(eventId: String) = {}

}

@Composable
fun rememberEventsFeedCoordinator(
    navController: NavHostController,
    viewModel: EventsFeedViewModel = hiltViewModel()
): EventsFeedCoordinator {
    return remember(viewModel) {
        EventsFeedCoordinator(
            viewModel = viewModel,
            navController = navController
        )
    }
}
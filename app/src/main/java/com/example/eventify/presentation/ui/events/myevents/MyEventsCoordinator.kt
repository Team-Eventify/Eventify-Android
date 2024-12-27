package com.example.eventify.presentation.ui.events.myevents

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.eventify.presentation.navigation.navgraphs.RootRouter

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class MyEventsCoordinator(
    val viewModel: MyEventsViewModel
) {
    val screenStateFlow = viewModel.stateFlow

}

@Composable
fun rememberMyEventsCoordinator(
    viewModel: MyEventsViewModel = hiltViewModel()
): MyEventsCoordinator {
    return remember(viewModel) {
        MyEventsCoordinator(
            viewModel = viewModel
        )
    }
}
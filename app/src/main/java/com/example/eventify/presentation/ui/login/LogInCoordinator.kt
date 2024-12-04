package com.example.eventify.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.navgraphs.AuthRouter

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LogInCoordinator(
    val viewModel: LogInViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun navigateToRegister(){
        viewModel.navigateToRegister()
    }

}

@Composable
fun rememberLogInCoordinator(
    viewModel: LogInViewModel = hiltViewModel()
): LogInCoordinator {
    return remember(viewModel) {
        LogInCoordinator(
            viewModel = viewModel
        )
    }
}
package com.example.eventify.presentation.ui.login

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navgraphs.AuthRouter

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class LogInCoordinator(
    val navController: NavHostController,
    val viewModel: LogInViewModel
) {
    val screenStateFlow = viewModel.stateFlow

    fun navigateToRegister(){
        navController.navigate(AuthRouter.RegisterRoute)
    }

}

@Composable
fun rememberLogInCoordinator(
    navController: NavHostController,
    viewModel: LogInViewModel = hiltViewModel()
): LogInCoordinator {
    return remember(viewModel) {
        LogInCoordinator(
            navController = navController,
            viewModel = viewModel
        )
    }
}
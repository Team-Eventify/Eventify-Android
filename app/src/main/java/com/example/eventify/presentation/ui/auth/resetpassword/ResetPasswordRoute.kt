package com.example.eventify.presentation.ui.auth.resetpassword

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.LocaleSnackbarState
import com.example.eventify.presentation.ui.auth.resetpassword.state.ResetPasswordListener

@Composable
fun ResetPasswordRoute(
    navController: NavHostController
) {
    val viewModel = hiltViewModel<ResetPasswordViewModel>()
    val uiState by viewModel.stateFlow.collectAsState()
    val topBarState = LocalTopBarState.current
    val snackBarState = LocaleSnackbarState.current

    val listener = object : ResetPasswordListener {
        override fun submit() {

        }

        override fun changeEmail(email: String) {
            viewModel.onChangeEmail(email)
        }

    }

    LaunchedEffect(Unit) {
        topBarState.hide()
    }

    ResetPasswordScreen(uiState, listener)
}

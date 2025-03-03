package com.example.eventify.presentation.ui.account.profileedit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.ui.account.profileedit.state.ProfileEditListener
import com.example.eventify.presentation.ui.account.profileedit.state.UiState
import com.example.eventify.presentation.ui.common.DefaultTopAppBar

@Composable
fun ProfileEditRoute(
    navController: NavHostController,
) {
    val viewModel = hiltViewModel<ProfileEditViewModel>()
    val uiState by viewModel.stateFlow.collectAsStateWithLifecycle()
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val features = LocalFeaturesProvider.current.features

    val listener = object : ProfileEditListener {
        override fun onSubmit() {
            viewModel.saveUser()
        }

        override fun onChangeCategoryFilterActive(categoryId: String, value: Boolean) {
            viewModel.changeCategoryFilterActive(categoryId, value)
        }

        override fun onChangeEmail(email: String) {
            viewModel.changeUserEmail(email)
        }

        override fun onChangeFirstName(firstName: String) {
            viewModel.changeUserFirstName(firstName)
        }

        override fun onChangeLastName(lastName: String) {
            viewModel.changeUserLastName(lastName)
        }

        override fun onChangeTelegram(telegram: String) {
            viewModel.changeUserTelegram(telegram)
        }

        override fun onDeleteAccount() {
            viewModel.deleteAccount()
        }

    }

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.profile_edit_title),
                size = TopBarSize.SMALL,
            )
        )
    }
    when (uiState) {
        UiState.Error -> {}
        UiState.Loading -> {}
        is UiState.ShowProfileEdit -> {
            ProfileEditScreen(uiState, listener)
        }
    }

}
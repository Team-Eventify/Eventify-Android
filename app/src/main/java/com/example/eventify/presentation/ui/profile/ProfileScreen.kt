package com.example.eventify.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.data.models.UserInfo
import com.example.eventify.presentation.models.UserResult
import com.example.eventify.presentation.models.UserUiState
import com.example.eventify.presentation.ui.navgraphs.ProfileRouter
import com.example.eventify.presentation.viewmodels.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    ProfileScreenComponent(
        uiState = viewModel.uiState,
        currentUser = viewModel.user,
        onLoadCurrentUser = viewModel::loadUserInfo,
        navController = navController,
        userResult = viewModel.loadUserResult
    )
}


@Composable
fun ProfileScreenComponent(
    uiState: UserUiState,
    currentUser: UserInfo?,
    userResult: UserResult,
    onLoadCurrentUser: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        LaunchedEffect(true) {
            onLoadCurrentUser()
        }

        UserProfilePanel(
            user = currentUser,
            onClick = {
                navController.navigate(ProfileRouter.EditProfile)
            }
        )
    }
}

@Preview(name = "ProfileScreen", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewProfileScreen() {
    ProfileScreenComponent(
        uiState = UserUiState(
            email = "werwer",
            firstName = "xxcv",
            lastName = "vfvf",
            middleName = "asda",
            telegramName = "vsdvds",
        ),
        currentUser = null,
        userResult = UserResult.Idle,
        onLoadCurrentUser = {},
        navController = rememberNavController()
    )
}
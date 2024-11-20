package com.example.eventify.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.data.models.UserInfo
import com.example.eventify.presentation.models.UserResult
import com.example.eventify.presentation.models.UserUiState
import com.example.eventify.presentation.navigation.ProfileRouter
import com.example.eventify.presentation.navigation.RootRouter
import com.example.eventify.presentation.viewmodels.UserViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: UserViewModel = hiltViewModel(),
    rootNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    ProfileScreenComponent(
        uiState = viewModel.uiState,
        currentUser = viewModel.user,
        onLoadCurrentUser = viewModel::loadUserInfo,
        navController = navController,
        rootNavController = rootNavController,
        userResult = viewModel.loadUserResult,
        onLogOut = viewModel::logOut
    )
}


@Composable
fun ProfileScreenComponent(
    uiState: UserUiState,
    currentUser: UserInfo?,
    userResult: UserResult,
    onLoadCurrentUser: () -> Unit,
    onLogOut: () -> Unit,
    navController: NavHostController,
    rootNavController: NavHostController,
    modifier: Modifier = Modifier
) {
    val showLogOutDialog = remember { mutableStateOf(false) }

    if (showLogOutDialog.value){
        LogOutDialog(
            onDismissRequest = {
                showLogOutDialog.value = false
            },
            onLogOut = {
                onLogOut()
                rootNavController.navigate(RootRouter.Auth)
            }
        )
    }


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
        Button(onClick = {
            showLogOutDialog.value = true
        }) {
            Text(text = "Выйти")
        }
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
        navController = rememberNavController(),
        onLogOut = {},
        rootNavController = rememberNavController()
    )
}
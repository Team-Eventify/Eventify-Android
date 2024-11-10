package com.example.eventify.presentation.ui.navgraphs

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import com.example.eventify.presentation.ui.profile.ProfileScreen
import com.example.eventify.presentation.viewmodels.UserViewModel
import kotlinx.serialization.Serializable


fun NavGraphBuilder.profileNavigationGraph(
    navController: NavHostController,
    viewModel: UserViewModel,
){

    navigation<HomeRouter.Profile>(
        startDestination = ProfileRouter.DetailProfile,
    ){
        composable<ProfileRouter.DetailProfile> {
            ProfileScreen(navController, viewModel = viewModel)
        }
        composable<ProfileRouter.EditProfile> {
            ProfileEditScreen(navController, viewModel = viewModel)
        }
    }
}


sealed class ProfileRouter{
    @Serializable
    data object DetailProfile : ProfileRouter()
    @Serializable
    data object EditProfile : ProfileRouter()
}
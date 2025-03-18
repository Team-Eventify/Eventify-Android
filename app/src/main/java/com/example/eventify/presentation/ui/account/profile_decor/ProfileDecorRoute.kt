package com.example.eventify.presentation.ui.account.profile_decor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.example.eventify.R
import com.example.eventify.presentation.LocalTopBarState
import com.example.eventify.presentation.MainActivity
import com.example.eventify.presentation.TopBarAction
import com.example.eventify.presentation.TopBarSize
import com.example.eventify.presentation.TopBarState
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme

@Composable
fun ProfileDecorRoute(navController: NavController) {
    val topBarState = LocalTopBarState.current
    val context = LocalContext.current
    val activity = context as? MainActivity

    val listener = object : ProfileDecorRouteListener {
        override fun onBackClick() {
            navController.navigateUp()
        }
        override fun changeTheme(typeOfTheme: TypesTheme) {
            activity?.changeTheme(typeOfTheme)
        }
    }

    ProfileDecorScreen(listener)

    LaunchedEffect(Unit) {
        topBarState.setUp(
            TopBarState.Base(
                title = context.getString(R.string.design_section_title),
                size = TopBarSize.SMALL,
                leftAction = TopBarAction(
                    iconRes = R.drawable.ic_chevron_right,
                    onClick = listener::onBackClick
                )
            )
        )
    }
}
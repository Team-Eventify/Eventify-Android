package com.example.eventify.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.eventify.presentation.navigation.HomeNavigationGraph
import com.example.eventify.presentation.ui.shared.BottomNavigationBar

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                navController = navController
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier.padding(PaddingValues(bottom = innerPadding.calculateBottomPadding()))
            ) {
                HomeNavigationGraph(navController = navController, rootNavController = rootNavController)
            }

    }
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    MainScreen(
        rootNavController = rememberNavController()
    )
}
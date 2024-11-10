package com.example.eventify.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.eventify.domain.usecases.CurrentUserUseCase
import com.example.eventify.presentation.ui.events.EventDetailScreen
import com.example.eventify.presentation.ui.events.EventsFeedScreen
import com.example.eventify.presentation.ui.login.LogInScreen
import com.example.eventify.presentation.ui.login.RegisterScreen
import com.example.eventify.presentation.ui.navgraphs.RootNavGraph
import com.example.eventify.presentation.ui.navgraphs.RootRouter
import com.example.eventify.presentation.ui.profile.ProfileEditScreen
import com.example.eventify.presentation.ui.register.CategorySelectScreen
import com.example.eventify.presentation.ui.shared.EventCard
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sessionViewModel: SessionViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventifyTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    sessionViewModel.checkLoggedIn()

                    RootNavGraph(
                        startDestination = if (sessionViewModel.isLoggedIn) RootRouter.Home else RootRouter.Auth,
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

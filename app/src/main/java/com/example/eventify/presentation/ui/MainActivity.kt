package com.example.eventify.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
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
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventifyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RootNavGraph(
                        navController = rememberNavController()
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EventifyTheme {
        Greeting("Android")
    }
}
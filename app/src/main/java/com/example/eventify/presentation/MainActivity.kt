package com.example.eventify.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.presentation.navigation.NavigationAction
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.HomeRouter
import com.example.eventify.presentation.navigation.navgraphs.MainNavHost
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.shared.BottomNavigationBar
import com.example.eventify.presentation.ui.shared.OfflineComponent
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.ObserveAsState
import com.example.eventify.presentation.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import rememberConnectivityState
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val sessionViewModel: SessionViewModel by viewModels()
    @Inject lateinit var navigator: Navigator
    @Inject lateinit var tokenManager: TokenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EventifyTheme {

                window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()

                val navController = rememberNavController()
                val currentDist = if (tokenManager.isValidData()) RootRouter.HomeRoute else RootRouter.HomeRoute


                ObserveAsState(flow = navigator.navigationActions) { action ->
                    when(action) {
                        is NavigationAction.Navigate -> navController.navigate(
                            action.destination
                        ) {
                            action.navOptions(this)
                        }
                        NavigationAction.NavigateUp -> navController.navigateUp()
                    }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    sessionViewModel.checkLoggedIn()

                    val snackbarHostState = remember { SnackbarHostState() }
                    val scope = rememberCoroutineScope()


                    ObserveAsState(
                        flow = SnackbarController.events,
                        snackbarHostState
                    ) { event ->
                        scope.launch {
                            snackbarHostState.currentSnackbarData?.dismiss()
                            val result = snackbarHostState.showSnackbar(
                                message = event.message,
                                withDismissAction = event.withDismissAction,
                                actionLabel = event.action?.name,
                                duration = event.duration
                            )
                            if (result == SnackbarResult.ActionPerformed){
                                event.action?.action?.invoke()
                            }
                        }
                    }

                    Scaffold(
                        snackbarHost = {
                            SnackbarHost(hostState = snackbarHostState)
                        },
                        bottomBar = {
                            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                            val homeRoutes = HomeRouter::class.sealedSubclasses.mapNotNull { it.objectInstance }
                            val isHomeRoute = homeRoutes.any {
                                it::class.java.canonicalName == currentRoute
                            }
                            if (isHomeRoute){
                                BottomNavigationBar(navController = navController)
                            }
                        }
                    ) { innerPadding ->

                        MainNavHost(
                            navController = navController,
                            startDestination = currentDist
                        )
                    }

                    val connectionState by rememberConnectivityState()

                    val isConnected by remember(connectionState) {
                        derivedStateOf {
                            connectionState === NetworkConnectionState.Available
                        }
                    }

                    if (!isConnected){
                        OfflineComponent()
                    }

                }
            }
        }
    }
}


package com.example.eventify.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.example.eventify.domain.SessionManager
import com.example.eventify.domain.di.RequestsSessionManager
import com.example.eventify.presentation.models.ScaffoldViewState
import com.example.eventify.presentation.navigation.NavigationAction
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.MainNavHost
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.shared.BottomNavigationBar
import com.example.eventify.presentation.ui.shared.EventifySnackbar
import com.example.eventify.presentation.ui.shared.OfflineScreen
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.ObserveAsState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import rememberConnectivityState
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigator: Navigator

    @RequestsSessionManager
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        RequestNotificationPermission()

        setContent {
            EventifyTheme {
                LaunchedEffect(Unit) {
                    enableEdgeToEdge(
                        statusBarStyle = SystemBarStyle.light(
                            Color.Transparent.toArgb(), Color.Transparent.toArgb()
                        ),
                        navigationBarStyle = SystemBarStyle.light(
                            Color(0xFF232326).toArgb(), Color(0xFF232326).toArgb()
                        )
                    )
                }


                val navController = rememberNavController()

                val currentDist =
                    runBlocking { if (sessionManager.isLoggedIn()) RootRouter.HomeRoute else RootRouter.OnboardingRoute }

                ObserveAsState(flow = navigator.navigationActions) { action ->
                    when (action) {
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
                            if (result == SnackbarResult.ActionPerformed) {
                                event.action?.action?.invoke()
                            }
                        }
                    }

                    val scaffoldState = remember { mutableStateOf(ScaffoldViewState()) }


                    Scaffold(
                        modifier = scaffoldState.value.modifier,
                        topBar = scaffoldState.value.topBar,
                        bottomBar = {
                            if (scaffoldState.value.showBottomBar)
                                BottomNavigationBar(navController)
                        },
                        snackbarHost = {
                            SnackbarHost(snackbarHostState){
                                EventifySnackbar(it)
                            }
                        },
                        floatingActionButton = scaffoldState.value.floatingActionButton,
                        floatingActionButtonPosition = scaffoldState.value.floatingActionButtonPosition
                    ) { innerPadding ->
                        MainNavHost(
                            navController = navController,
                            startDestination = currentDist,
                            scaffoldViewState = scaffoldState,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    val connectionState by rememberConnectivityState()

                    val isConnected by remember(connectionState) {
                        derivedStateOf {
                            connectionState === NetworkConnectionState.Available
                        }
                    }

                    if (!isConnected) {
                        OfflineScreen(
                            onRefreshState = {}
                        )
                    }

                }
            }
        }
    }

    private fun RequestNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            val hasPermission = ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED

            if (!hasPermission){
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    0
                )
            }
        }
    }
    
}


package com.example.eventify.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.domain.SessionManager
import com.example.eventify.domain.di.RequestsSessionManager
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.navigation.NavigationAction
import com.example.eventify.presentation.navigation.Navigator
import com.example.eventify.presentation.navigation.navgraphs.Destination
import com.example.eventify.presentation.navigation.navgraphs.MainNavHost
import com.example.eventify.presentation.navigation.navgraphs.RootRouter
import com.example.eventify.presentation.ui.SnackbarController
import com.example.eventify.presentation.ui.common.bottomBar.BottomNavigationBar
import com.example.eventify.presentation.ui.common.EventifySnackbar
import com.example.eventify.presentation.ui.common.screens.NoInternetConnectionScreen
import com.example.eventify.presentation.ui.common.topBar.EventifyTopAppBar
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.ObserveAsState
import com.example.eventify.presentation.utils.isShowOnboarding
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

    @SharedStorage
    @Inject
    lateinit var localeStorage: LocaleStorage

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        RequestNotificationPermission()

        setContent {
            val topBarState = rememberTopBarState()
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()
            val connectionState by rememberConnectivityState()
            val isConnected by remember(connectionState) {
                derivedStateOf {
                    connectionState === NetworkConnectionState.Available
                }
            }

            EventifyTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = false,
                LocaleImageLoader provides imageLoader,
                    LocalTopBarState provides topBarState,
                    LocalFeaturesProvider provides application.featuresProvider
                ) {
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

                    Scaffold(
                        topBar = {
                            AnimatedVisibility(topBarState.isVisible) {
                                EventifyTopAppBar(state = topBarState.topBarState)
                            }
                        },
                        bottomBar = {
                            BottomNavigationBar(navController)
                        },
                        snackbarHost = {
                            SnackbarHost(snackbarHostState){
                                EventifySnackbar(it)
                            }
                        },
                    ) { innerPadding ->
                        MainNavHost(
                            navController = navController,
                            startDestination = getDefaultDestination(),
                            modifier = Modifier.padding(innerPadding)
                        )
                    }

                    // Connectivity
                    if (!isConnected) {
                        NoInternetConnectionScreen()
                    }

                }
            }
        }
    }
    private fun getDefaultDestination(): Destination {
        return runBlocking {
            when {
                localeStorage.isShowOnboarding() -> RootRouter.OnboardingRoute
                sessionManager.isLoggedIn() -> RootRouter.HomeRoute
                else -> RootRouter.AuthRoute
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


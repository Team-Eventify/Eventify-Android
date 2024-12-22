package com.example.eventify.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.magnifier
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.eventify.data.repositories.tokens.TokenManager
import com.example.eventify.domain.SessionManager
import com.example.eventify.domain.di.RequestsSessionManager
import com.example.eventify.presentation.models.ScaffoldViewState
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
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.google.firebase.Firebase
import com.google.firebase.messaging.messaging
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.tasks.await
import rememberConnectivityState
import timber.log.Timber
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

                LaunchedEffect(true) {
                    lifecycleScope.launch {
                        val token = Firebase.messaging.token.await()
                        Timber.d(token)
                    }
                }


                window.navigationBarColor = MaterialTheme.colorScheme.background.toArgb()
                window.statusBarColor = MaterialTheme.colorScheme.background.toArgb()

                val navController = rememberNavController()

                val currentDist =
                    runBlocking { if (sessionManager.isLoggedIn()) RootRouter.HomeRoute else RootRouter.AuthRoute }

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

                    val scaffoldState = remember { mutableStateOf(ScaffoldViewState(
                        bottomBar = { BottomNavigationBar(navController) },
                        showBottomBar = true
                    )) }

                    Scaffold(
                        modifier = scaffoldState.value.modifier,
                        topBar = scaffoldState.value.topBar,
                        bottomBar = { if (scaffoldState.value.showBottomBar) scaffoldState.value.bottomBar else {} },
                        snackbarHost = scaffoldState.value.snackbarHost,
                        floatingActionButton = scaffoldState.value.floatingActionButton,
                        floatingActionButtonPosition = scaffoldState.value.floatingActionButtonPosition
                    ) { innerPadding ->
                        MainNavHost(
                            navController = navController,
                            startDestination = currentDist,
                            scaffoldViewState = scaffoldState
                        )
                    }

//                    Scaffold(
//                        snackbarHost = {
//                            SnackbarHost(hostState = snackbarHostState)
//                        },
//                        bottomBar = {
//                            val currentRoute =
//                                navController.currentBackStackEntryAsState().value?.destination?.route
//                            val homeRoutes =
//                                HomeRouter::class.sealedSubclasses.mapNotNull { it.objectInstance }
//                            val isHomeRoute = homeRoutes.any {
//                                it::class.java.canonicalName == currentRoute
//                            }
//                            if (isHomeRoute) {
//                                BottomNavigationBar(navController = navController)
//                            }
//                        }
//                    ) { innerPadding ->
//
//                        MainNavHost(
//                            navController = navController,
//                            startDestination = currentDist
//                        )
//                    }

                    val connectionState by rememberConnectivityState()

                    val isConnected by remember(connectionState) {
                        derivedStateOf {
                            connectionState === NetworkConnectionState.Available
                        }
                    }

                    if (!isConnected) {
                        OfflineComponent()
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


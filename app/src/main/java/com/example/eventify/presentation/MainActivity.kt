package com.example.eventify.presentation

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.domain.SessionManager
import com.example.eventify.domain.di.RequestsSessionManager
import com.example.eventify.presentation.navigation.LocalFeaturesProvider
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import com.example.eventify.presentation.ui.events.eventsfeed.EventsRootPath
import com.example.eventify.presentation.navigation.MainNavHost
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
import com.example.eventify.presentation.ui.auth.onboarding.OnBoardingPath
import com.example.eventify.presentation.ui.common.EventifySnackbar
import com.example.eventify.presentation.ui.common.bottomBar.BottomNavBar
import com.example.eventify.presentation.ui.common.screens.NoInternetConnectionScreen
import com.example.eventify.presentation.ui.common.topBar.EventifyTopAppBar
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.utils.isShowOnboarding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.runBlocking
import rememberConnectivityState
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequestsSessionManager
    @Inject
    lateinit var sessionManager: SessionManager

    @SharedStorage
    @Inject
    lateinit var localeStorage: LocaleStorage

    @Inject
    lateinit var imageLoader: ImageLoader

    private lateinit var isDarkTheme: MutableState<Boolean?>

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        RequestNotificationPermission()

        setContent {
            val topBarState = rememberTopBarState()
            val snackbarHostState = remember { SnackbarHostState() }
            val navController = rememberNavController()
            val connectionState by rememberConnectivityState()
            val isConnected by remember(connectionState) {
                derivedStateOf {
                    connectionState === NetworkConnectionState.Available
                }
            }

            isDarkTheme = remember { mutableStateOf(null) }
            loadTheme()

            EventifyTheme(
                darkTheme = if (isDarkTheme.value == null) isSystemInDarkTheme() else isDarkTheme.value!!,
                dynamicColor = false,
                LocaleImageLoader provides imageLoader,
                    LocalTopBarState provides topBarState,
                    LocalFeaturesProvider provides application.featuresProvider,
                    LocaleSnackbarState provides snackbarHostState,
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

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        topBar = {
                            AnimatedVisibility(topBarState.isVisible) {
                                EventifyTopAppBar(state = topBarState.topBarState)
                            }
                        },
                        bottomBar = {
                            BottomNavBar(navController)
                        },
                        snackbarHost = {
                            SnackbarHost(snackbarHostState){
                                EventifySnackbar(it)
                            }
                        },
                    ) { innerPadding ->
                        MainNavHost(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding),
                            startDestination = getStartDis()
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

    private fun getStartDis(): String {
        return runBlocking {
            when {
                localeStorage.isShowOnboarding() -> OnBoardingPath.baseRoute
                sessionManager.isLoggedIn() -> EventsRootPath.baseRoute
                else -> AuthRootPath.baseRoute
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

    private fun loadTheme() {
        val num: Int = localeStorage.getInt(StorageKeys.TYPE_THEME, -1)

        when (num) {
            -1 -> isDarkTheme.value = null
            0 -> isDarkTheme.value = true
            1 -> isDarkTheme.value = false
        }
    }

    fun changeTheme(typeOfTheme: TypesTheme) {
        when (typeOfTheme) {
            TypesTheme.SYSTEM_THEME -> {
                localeStorage.remove(StorageKeys.TYPE_THEME)
                isDarkTheme.value = null
            }
            TypesTheme.LIGHT_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.LIGHT_THEME.ordinal)
                isDarkTheme.value = false
            }
            TypesTheme.DARK_THEME -> {
                localeStorage.put(StorageKeys.TYPE_THEME, TypesTheme.DARK_THEME.ordinal)
                isDarkTheme.value = true
            }
        }
    }
}


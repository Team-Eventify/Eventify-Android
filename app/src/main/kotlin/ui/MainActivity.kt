package ui

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.navigation.compose.rememberNavController
import coil3.ImageLoader
import com.example.eventify.featuresProvider
import com.example.eventify.uikit.R
import core.common.extentions.isShowOnboarding
import core.common.navigation.AuthRoot
import core.common.navigation.EventsRoot
import uikit.components.screens.NoInternetConnectionScreen
import ui.navigation.navgraphs.MainNavHost
import core.common.secure.AuthorizeType
import core.common.storages.LocaleStorage
import core.common.storages.SharedStorage
import core.featureManager.LocalFeaturesProvider
import core.featureManager.clearNavigate
import core.featureManager.navigateToFeature
import dagger.hilt.android.AndroidEntryPoint
import domain.SessionManager
import feature.eventFeed.api.EventFeedPath
import feature.eventFeed.api.EventsFeedEntry
import feature.login.api.LoginEntry
import feature.myEvents.api.MyEventsPath
import feature.onboarding.api.OnBoardingPath
import feature.profile.api.ProfilePath
import feature.search.api.SearchPath
import feature.searchResult.api.SearchDetailPath
import kotlinx.coroutines.runBlocking
import rememberConnectivityState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.LocalSnackbarState
import uikit.LocaleImageLoader
import uikit.components.bottomBar.BottomBarItem
import uikit.components.bottomBar.BottomNavBar
import uikit.components.snackbar.AppSnackbarState
import uikit.components.snackbar.SnackbarHost
import uikit.components.topBar.EventifyTopAppBar
import uikit.components.topBar.LocalTopBarState
import uikit.components.topBar.rememberTopBarState
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RootViewModel by viewModels()

    @Inject
    lateinit var sessionManager: SessionManager

    @SharedStorage
    @Inject
    lateinit var localeStorage: LocaleStorage

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        enableEdgeToEdge(
            navigationBarStyle = SystemBarStyle.dark(
                Color.Transparent.toArgb()
            )
        )
        super.onCreate(savedInstanceState)
        RequestNotificationPermission()

        setContent {
            val topBarState = rememberTopBarState()
            val scope = rememberCoroutineScope()
            val snackbarHostState = remember { AppSnackbarState(scope) }
            val navController = rememberNavController()
            val connectionState by rememberConnectivityState()
            val lifecycleOwner = LocalLifecycleOwner.current
            val dims = LocalDimentions.current
            val isConnected by remember(connectionState) {
                derivedStateOf {
                    connectionState === NetworkConnectionState.Available
                }
            }

            val startDist = remember {
                getStartDis()
            }

            val items = remember {
                listOf(
                    BottomBarItem(
                        titleResId = R.string.home,
                        iconResId = R.drawable.ic_house,
                        route = EventFeedPath.route
                    ),
                    BottomBarItem(
                        titleResId = R.string.search,
                        iconResId = R.drawable.ic_magnifying_glass,
                        route = SearchPath.route,
                        nestedRoutes = listOf(
                            SearchDetailPath.route,
                        )
                    ),
                    BottomBarItem(
                        titleResId = R.string.my_events,
                        iconResId = R.drawable.ic_bookmark,
                        route = MyEventsPath.route
                    ),
                    BottomBarItem(
                        titleResId = R.string.profile,
                        iconResId = R.drawable.ic_person,
                        route = ProfilePath.route
                    ),
                )
            }

            val isDarkTheme by viewModel.themeManager.isDarkTheme.collectAsState()

            EventifyTheme(
                darkTheme = isDarkTheme ?: isSystemInDarkTheme(),
                dynamicColor = false,
                    LocaleImageLoader provides imageLoader,
                    LocalTopBarState provides topBarState,
                    LocalFeaturesProvider provides application.featuresProvider,
                    LocalSnackbarState provides snackbarHostState,
                ) {

                LaunchedEffect(Unit) {
                    viewModel.authState
                        .flowWithLifecycle(lifecycleOwner.lifecycle, Lifecycle.State.CREATED)
                        .collect { state ->
                            when (state) {
                                AuthorizeType.Authorized -> {
                                    application.featuresProvider.features.navigateToFeature<EventsFeedEntry>(navController)
                                }
                                AuthorizeType.Unauthorized -> {
                                    viewModel.logOut()
                                    application.featuresProvider.features.clearNavigate<LoginEntry>(navController)
                                }
                                null -> Unit
                            }
                        }
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    Scaffold(
                        topBar = {
                            AnimatedVisibility(topBarState.isVisible) {
                                EventifyTopAppBar(state = topBarState.topBarState)
                            }
                        },
                        bottomBar = {
                            BottomNavBar(items, navController)
                        },
                        contentWindowInsets = WindowInsets.safeDrawing,
                    ) { innerPadding ->
                        MainNavHost(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding),
                            startDestination = startDist,
                        )
                    }

                    SnackbarHost(
                        modifier = Modifier
                            .safeDrawingPadding()
                            .padding(dims.windowPaddings)
                    )

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
                sessionManager.isLoggedIn() -> EventsRoot.baseRoute
                else -> AuthRoot.baseRoute
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


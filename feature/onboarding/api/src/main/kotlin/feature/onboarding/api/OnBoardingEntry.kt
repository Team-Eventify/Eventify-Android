package feature.onboarding.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.auth.login.AuthRootPath
import com.example.eventify.presentation.ui.auth.register.RegisterRoute
import javax.inject.Inject

val OnBoardingPath = BaseDestination("onboarding")

interface OnBoardingEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = OnBoardingPath
}


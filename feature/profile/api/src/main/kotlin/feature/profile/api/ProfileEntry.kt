package feature.profile.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import javax.inject.Inject

val AccountRootPath = BaseDestination("account")

val ProfilePath = AccountRootPath.child("profile")


interface ProfileEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfilePath
}



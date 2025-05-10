package feature.profileEdit.api

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import javax.inject.Inject

val ProfileEditPath = AccountRootPath.child("edit")

interface ProfileEditEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfileEditPath
}


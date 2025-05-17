
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.navigation.BaseDestination
import com.example.eventify.presentation.navigation.ComposableFeatureEntry
import com.example.eventify.presentation.ui.account.profile.AccountRootPath
import javax.inject.Inject

class ProfileDecorEntryImpl @Inject constructor(): ProfileDecorEntry {

    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileDecorRoute(navController)
    }
}
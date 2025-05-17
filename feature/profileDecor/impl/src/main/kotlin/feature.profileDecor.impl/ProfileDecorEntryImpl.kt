
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.eventify.presentation.ui.account.profile_decor.ProfileDecorEntry
import com.example.eventify.presentation.ui.account.profile_decor.ProfileDecorRoute
import javax.inject.Inject

class ProfileDecorEntryImpl @Inject constructor(): ProfileDecorEntry {

    @Composable
    override fun Composable(navController: NavHostController) {
        ProfileDecorRoute(navController)
    }
}
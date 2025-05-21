package feature.profile.impl.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.feature.profile.impl.R as ProfileR
import domain.models.UserShortInfo
import feature.profile.api.ProfileListener
import feature.profile.impl.components.LogOutDialog
import feature.profile.impl.components.UserProfilePanel
import feature.profile.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.ActionSettingsItem
import uikit.components.NavigationSettingsItem
import uikit.components.SettingsBlock
import com.example.eventify.uikit.R as UiKitR


@Composable
internal fun ProfileScreen(
    state: UiState.ShowProfile,
    actions: ProfileListener,
) {
    val openLogOutDialog = remember { mutableStateOf(false) }
    val dimmentions = LocalDimentions.current

    if (openLogOutDialog.value) {
        LogOutDialog(
            onDismissRequest = {
                openLogOutDialog.value = false
            },
            onConfirmation = {
                openLogOutDialog.value = false
                actions.onLogOut()
            }
        )
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(dimmentions.windowPaddings)
    ) {
        UserProfilePanel(
            firstName = state.userInfo.firstName,
            lastName = state.userInfo.lastName,
            onClick = actions::navigateToProfileEdit,
            modifier = Modifier
        )

        SettingsBlock {
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.help_and_support),
                onClick = actions::navigateToSupport
            )
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(ProfileR.string.decor_item_title),
                onClick = actions::navigateToDecor
            )
        }

        SettingsBlock {
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.about_app),
                onClick = actions::navigateToAppInfo,
            )
        }

        SettingsBlock {
            ActionSettingsItem(
                text = stringResource(UiKitR.string.log_out),
                onClick = {
                    openLogOutDialog.value = true
                }
            )
        }
    }
}

@Composable
@Preview(name = "ProfileScreen", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "ProfileScreen", uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileScreenPreview() {
    EventifyTheme {
        Surface {
            ProfileScreen(
                state = UiState.ShowProfile(
                    userInfo = UserShortInfo(
                        id = "",
                        firstName = "Иван",
                        lastName = "Иванов",
                        email = "ivanov@mail.ru"
                    )
                ),
                actions = object : ProfileListener {
                    override fun onLogOut() = Unit
                    override fun navigateToProfileEdit() = Unit
                    override fun navigateToAppInfo() = Unit
                    override fun navigateToDecor() = Unit
                    override fun navigateToSupport() = Unit
                }
            )
        }
    }
}


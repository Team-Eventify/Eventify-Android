package feature.profile.impl.ui

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
import feature.profile.api.ProfileListener
import feature.profile.impl.components.ActionSettingsItem
import feature.profile.impl.components.LogOutDialog
import feature.profile.impl.components.NavigationSettingsItem
import feature.profile.impl.components.SettingsBlock
import feature.profile.impl.components.UserProfilePanel
import feature.profile.impl.state.UiState
import uikit.EventifyTheme
import uikit.LocalDimentions


@Composable
fun ProfileScreen(
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
//                .shimmer(
//                    showShimmer = state.isLoading
//                )
        )
        SettingsBlock {
            NavigationSettingsItem(stringResource(R.string.notifications), onClick = {})
            HorizontalDivider()
            NavigationSettingsItem(stringResource(R.string.help_and_support))
        }

        SettingsBlock {
            NavigationSettingsItem(stringResource(R.string.about_app), onClick = actions::navigateToAppInfo)
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(R.string.to_rate),
            )
        }

        SettingsBlock {
            ActionSettingsItem(
                text = stringResource(R.string.log_out),
                onClick = {
                    openLogOutDialog.value = true
                }
            )
        }
    }
}

@Composable
@Preview(name = "Profile Default Dark")
private fun ProfileScreenPreview() {
    EventifyTheme(darkTheme = true) {
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
                }
            )
        }
    }
}


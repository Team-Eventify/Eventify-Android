package com.example.eventify.presentation.ui.account.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.ui.account.profile.components.ActionSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.SettingsBlock
import com.example.eventify.presentation.ui.account.profile.components.BaseSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.DeleteAccountDialog
import com.example.eventify.presentation.ui.account.profile.components.ImportantActionSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.LogOutDialog
import com.example.eventify.presentation.ui.account.profile.components.NavigationSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.UserProfilePanel
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ProfileScreen(
    state: ProfileState,
    actions: ProfileActions,
) {
    val openLogOutDialog = remember { mutableStateOf(false) }
    val openDeleteAccountDialog = remember { mutableStateOf(false) }


    when {
        openLogOutDialog.value -> {
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
        openDeleteAccountDialog.value -> {
            DeleteAccountDialog(
                onDismissRequest = {
                    openDeleteAccountDialog.value = false
                },
                onConfirmation = {
                    openLogOutDialog.value = false
                    actions.onDeleteAccount()
                }
            )
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        UserProfilePanel(
            firstName = state.userInfo?.firstName,
            lastName = state.userInfo?.lastName,
            onClick = actions.navigateToProfileEdit
        )
        SettingsBlock {
            NavigationSettingsItem(stringResource(R.string.notifications), onClick = {})
            HorizontalDivider()
            NavigationSettingsItem(stringResource(R.string.help_and_support))
        }

        SettingsBlock {
            NavigationSettingsItem(stringResource(R.string.about_app), onClick = {})
            HorizontalDivider()
            NavigationSettingsItem(stringResource(R.string.to_rate))
        }

        SettingsBlock {
            ActionSettingsItem(
                text = stringResource(R.string.log_out),
                onClick = {
                    openLogOutDialog.value = true
                }
            )
        }

        SettingsBlock {
            ImportantActionSettingsItem(
                text = stringResource(R.string.delete_account),
                onClick = {
                    openDeleteAccountDialog.value = true
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
                state = ProfileState(
                    userInfo = UserShortInfo(
                        id = "",
                        firstName = "Иван",
                        lastName = "Иванов",
                        middleName = "Иванович",
                        email = "ivanov@mail.ru"
                    )
                ),
                actions = ProfileActions.default()
            )
        }
    }
}


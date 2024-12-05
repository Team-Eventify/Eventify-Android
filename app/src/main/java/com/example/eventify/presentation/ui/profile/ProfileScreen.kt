package com.example.eventify.presentation.ui.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.ui.tempprofile.LogOutDialog
import com.example.eventify.presentation.ui.profile.components.UserProfilePanel
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ProfileScreen(
    state: ProfileState,
    actions: ProfileActions,
) {
    val showLogOutDialog = remember { mutableStateOf(false) }

    if (showLogOutDialog.value){
        LogOutDialog(
            onDismissRequest = {
                showLogOutDialog.value = false
            },
            onLogOut = actions.onLogOut
        )
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        UserProfilePanel(
            user = state.userInfo!!,
            onClick = actions.navigateToProfileEdit
        )
        Button(onClick = {
            showLogOutDialog.value = true
        }) {
            Text(text = "Выйти")
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


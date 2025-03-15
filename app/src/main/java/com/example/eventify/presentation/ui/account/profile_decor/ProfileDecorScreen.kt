package com.example.eventify.presentation.ui.account.profile_decor

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorRouteListener
import com.example.eventify.presentation.ui.account.profile_decor.state.ProfileDecorUiState
import com.example.eventify.presentation.ui.account.profile_decor.state.TypesTheme
import com.example.eventify.presentation.ui.common.BodyText
import com.example.eventify.presentation.ui.common.SubHeadingText
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions

@Composable
fun ProfileDecorScreen(state: ProfileDecorUiState, actions: ProfileDecorRouteListener) {
    val dimmentions = LocalDimentions.current

    Column(modifier = Modifier.fillMaxSize().padding(dimmentions.windowPaddings)) {
        SubHeadingText("Тема")
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Box(modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp)).clickable { actions.changeTheme(TypesTheme.SYSTEM_THEME)  }) {
                Column(modifier = Modifier.padding(vertical=15.dp, horizontal=20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.circle_lefthalf_filled),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    BodyText("Система")
                }
            }
            Box(modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp)).clickable { actions.changeTheme(TypesTheme.LIGHT_THEME)  }) {
                Column(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.sun_max_fill),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    BodyText("Светлая")
                }
            }
            Box(modifier = Modifier.border(2.dp, Color.Gray, shape = RoundedCornerShape(16.dp)).clickable { actions.changeTheme(TypesTheme.DARK_THEME)  }) {
                Column(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 20.dp)) {
                    Image(
                        painter = painterResource(R.drawable.sun_max_fill),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    BodyText("Темная")
                }
            }
        }
        HorizontalDivider(modifier = Modifier.padding(vertical=20.dp))

        SubHeadingText("Иконка приложения")

    }
}

@Composable
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun ProfileDecorScreenPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ProfileDecorScreen(state=ProfileDecorUiState.ProfileDecorData(typeOfTheme = null), actions=object : ProfileDecorRouteListener {
                override fun onBackClick() = Unit
                override fun changeTheme(typeOfTheme: TypesTheme) = Unit
            })
        }
    }
}
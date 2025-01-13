package com.example.eventify.presentation.ui.account.aboutapp

import android.graphics.BitmapFactory
import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.account.profile.components.NavigationSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.SettingsBlock
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun AboutAppScreen(
    state: AboutAppState,
    actions: AboutAppActions
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(.3f)
        )
        Text(
            text = state.versionName.asString()
        )

        SettingsBlock{
            NavigationSettingsItem(text = "Обратная связь")
        }
    }

}

@Composable
@Preview(name = "AboutApp")
private fun AboutAppScreenPreview() {
    EventifyTheme(darkTheme = true) {
        AboutAppScreen(
            state = AboutAppState(),
            actions = AboutAppActions()
        )
    }
}


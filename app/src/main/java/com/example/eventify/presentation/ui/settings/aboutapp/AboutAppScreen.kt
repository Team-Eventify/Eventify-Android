package com.example.eventify.presentation.ui.settings.aboutapp

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.settings.aboutapp.state.AboutAppListener
import com.example.eventify.presentation.ui.settings.aboutapp.state.AboutAppState
import com.example.eventify.presentation.ui.account.profile.components.NavigationSettingsItem
import com.example.eventify.presentation.ui.account.profile.components.SettingsBlock
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions

@Composable
fun AboutAppScreen(
    state: AboutAppState,
    actions: AboutAppListener
) {
    val dimmentions = LocalDimentions.current

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(dimmentions.windowPaddings)
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
            NavigationSettingsItem(text = stringResource(R.string.about_us))
        }

        SettingsBlock{
            NavigationSettingsItem(
                text = stringResource(R.string.privacy_policy),
                onClick = actions::goPrivacyPolicy,
            )
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(R.string.terms_of_use),
                onClick = actions::goTermsOfUse,
            )
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(R.string.information_security),
                onClick = actions::goToInformationSecurity,
            )
        }

        SettingsBlock{
            NavigationSettingsItem(
                text = stringResource(R.string.donate),
                onClick = actions::goToDonate,
            )
        }
    }

}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AboutAppScreenPreview() {
    EventifyTheme(dynamicColor = true) {
        AboutAppScreen(
            state = AboutAppState(),
            actions = object : AboutAppListener {
                override fun navigateUp() = Unit
                override fun goToAboutUs() = Unit
                override fun goPrivacyPolicy() = Unit
                override fun goTermsOfUse() = Unit
                override fun goToInformationSecurity() = Unit
                override fun goToDonate() = Unit
            }
        )
    }
}


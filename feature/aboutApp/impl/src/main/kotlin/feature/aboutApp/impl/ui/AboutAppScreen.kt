package feature.aboutApp.impl.ui

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
import feature.aboutApp.impl.state.AboutAppListener
import feature.aboutApp.impl.state.AboutAppState
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.NavigationSettingsItem
import uikit.components.SettingsBlock
import com.example.eventify.uikit.R as UiKitR



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
            painter = painterResource(UiKitR.drawable.ic_launcher_foreground),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth(.3f)
        )
        Text(
            text = state.versionName
        )

        SettingsBlock{
            NavigationSettingsItem(text = stringResource(UiKitR.string.about_us))
        }

        SettingsBlock{
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.privacy_policy),
                onClick = actions::goPrivacyPolicy,
            )
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.terms_of_use),
                onClick = actions::goTermsOfUse,
            )
            HorizontalDivider()
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.information_security),
                onClick = actions::goToInformationSecurity,
            )
        }

        SettingsBlock{
            NavigationSettingsItem(
                text = stringResource(UiKitR.string.donate),
                onClick = actions::goToDonate,
            )
        }
    }

}

@Composable
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
private fun AboutAppScreenPreview() {
    EventifyTheme {
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


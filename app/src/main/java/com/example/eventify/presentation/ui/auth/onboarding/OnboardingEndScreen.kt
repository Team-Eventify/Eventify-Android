package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SkipTextButton
import com.example.eventify.presentation.ui.shared.TitleText
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun OnboardingEndScreen(
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            TitleText(text = "Актуальные мероприятия", textAlign = TextAlign.Center)
            AnnotationText(text = "Все события вашего университета теперь собраны в одном удобном приложении.", textAlign = TextAlign.Center)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
        ) {
            PrimaryButton(onClick = { /*TODO*/ }) {
                PrimaryButtonText(text = stringResource(R.string.next))
            }
            SkipTextButton(onClick = { /*TODO*/ })
        }
    }
}

@Preview(name = "OnboardingEndScreen")
@Composable
private fun PreviewOnboardingEndScreen() {
    EventifyTheme(darkTheme = true) {
        Surface {
            OnboardingEndScreen()
        }
    }
}
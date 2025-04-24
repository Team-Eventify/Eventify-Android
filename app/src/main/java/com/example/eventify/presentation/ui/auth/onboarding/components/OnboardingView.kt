package com.example.eventify.presentation.ui.auth.onboarding.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.auth.onboarding.state.OnBoardingItem
import com.example.eventify.presentation.ui.common.BodyText
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun OnboardingView(
    itemState: OnBoardingItem,
    onSkip: () -> Unit,
    onNext: () -> Unit,
) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        PrimaryOnboardingImage(
            painter = itemState.primaryImage,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally

        ) {
            TitleText(
                text = itemState.title,
                textAlign = TextAlign.Center
            )

            itemState.body?.let {
                BodyText(it)
            }
        }


        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            PrimaryButton(
//                onClick = onNext
//            ) {
           //     PrimaryButtonText(itemState.primaryButtonText)
//            }
            SkipOnboardingButton(onClick = onSkip)
        }
    }
}

@Preview(name = "OnboardingView")
@Composable
private fun PreviewOnboardingViewDark() {
    EventifyTheme(darkTheme = true) {
        Surface(color = MaterialTheme.colorScheme.background) {
            OnboardingView(
               itemState = OnBoardingItem(
                   primaryImage = painterResource(R.drawable.ic_launcher_foreground),
                   title = "Актуальные мероприятия",
                   body = "Все события вашего университета теперь собраны в одном удобном приложении.",
               ),
                onNext = {},
                onSkip = {}
            )
        }
    }
}

@Preview(name = "OnboardingView")
@Composable
private fun PreviewOnboardingViewLight() {
    EventifyTheme(darkTheme = false) {
        Surface(color = MaterialTheme.colorScheme.background) {
            OnboardingView(
                itemState = OnBoardingItem(
                    primaryImage = painterResource(R.drawable.ic_launcher_foreground),
                    title = "Актуальные мероприятия",
                    body = "Все события вашего университета теперь собраны в одном удобном приложении.",
                ),
                onNext = {},
                onSkip = {}
            )
        }
    }
}
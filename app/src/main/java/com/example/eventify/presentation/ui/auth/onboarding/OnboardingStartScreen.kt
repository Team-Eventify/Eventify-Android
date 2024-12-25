package com.example.eventify.presentation.ui.auth.onboarding

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.PrimaryButtonText
import com.example.eventify.presentation.ui.shared.SkipTextButton
import com.example.eventify.presentation.ui.shared.buttons.PrimaryButton

@Composable
fun OnboardingStartScreen(

) {
    Column {
        PrimaryButton(onClick = { /*TODO*/ }) {
            PrimaryButtonText(text = stringResource(R.string.next))
        }
        SkipTextButton(onClick = { /*TODO*/ })
    }
}

@Preview(name = "OnboardingStartScreen")
@Composable
private fun PreviewOnboardingStartScreen() {
    OnboardingStartScreen()
}
package com.example.eventify.presentation.ui.auth.onboarding.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun SkipOnboardingButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier
            .composed { modifier }
    ) {
        Text(
            text = stringResource(R.string.skip),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textDecoration = TextDecoration.Underline
        )
    }
}

@Preview(name = "SkipOnboardingButton")
@Composable
private fun PreviewSkipOnboardingButton() {
    EventifyTheme(darkTheme = true) {
        SkipOnboardingButton(
            onClick = {}
        )
    }
}
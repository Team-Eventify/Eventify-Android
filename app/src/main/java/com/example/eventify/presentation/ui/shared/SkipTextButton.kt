package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun SkipTextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    text: String = stringResource(R.string.skip)
) {
    TextButton(
        onClick = onClick,
        modifier = Modifier.then(modifier)
    ) {
        Text(
            text = text,
            textDecoration = TextDecoration.Underline,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Preview(name = "SkipTextButtonDark")
@Composable
private fun PreviewSkipTextButtonDarkPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            SkipTextButton(
                onClick = {}
            )
        }
    }
}

@Preview(name = "SkipTextButtonLight")
@Composable
private fun PreviewSkipTextButtonLightPreview() {
    EventifyTheme {
        Surface {
            SkipTextButton(
                onClick = {}
            )
        }
    }
}
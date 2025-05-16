package uikit.components.buttons

import android.content.res.Configuration
import android.graphics.Paint.Align
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.space10
import uikit.space48
import uikit.space56


@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
) {
    DefaultButton(
        text = text,
        modifier = Modifier
            .then(modifier),
        fillMaxWidth = true,
        color = color,
        isEnabled = isEnabled,
        onClick = onClick,
    )
}

@Composable
fun PrimaryButtonWithLoader(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    isLoading: Boolean = false,
    color: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,
) {
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(space48)
                .clip(RoundedCornerShape(space10))
                .background(color)
                .then(modifier)
        ) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier
                    .align(Alignment.Center)
            )
        }
    } else {
        PrimaryButton(
            text = text,
            onClick = onClick,
            isEnabled = isEnabled,
            color = color,
            modifier = Modifier
                .then(modifier)
        )
    }
}

@Preview
@Composable
private fun PreviewPrimaryButtonWithLoader() {
    EventifyTheme {
        PrimaryButtonWithLoader(
            text = "",
            onClick = {},
            isLoading = true,
        )
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewPrimaryButtonDark(
    @PreviewParameter(PrimaryButtonPreviewProvider::class) params: PrimaryButtonParams
) {
    EventifyTheme {
        PrimaryButton(
            text = "Text",
            onClick = {},
            isEnabled = params.enabled,
        )
    }
}

private data class PrimaryButtonParams(
    val enabled: Boolean,
    val isLoading: Boolean,
)


private class PrimaryButtonPreviewProvider : PreviewParameterProvider<PrimaryButtonParams> {
    override val values: Sequence<PrimaryButtonParams>
        get() = sequenceOf(
            PrimaryButtonParams(
                enabled = true,
                isLoading = false
            ),
            PrimaryButtonParams(
                enabled = false,
                isLoading = false,
            ),
            PrimaryButtonParams(
                enabled = true,
                isLoading = true,
            ),
        )
}
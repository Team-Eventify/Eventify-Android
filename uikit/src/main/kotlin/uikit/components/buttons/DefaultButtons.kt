package uikit.components.buttons

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import uikit.EventifyTheme
import uikit.TypographyKit
import uikit.space10
import uikit.space48
import uikit.utils.conditional


@Composable
internal fun DefaultButton(
    text: String,
    @DrawableRes iconRes: Int? = null,
    isEnabled: Boolean = true,
    fillMaxWidth: Boolean = true,
    color: Color = MaterialTheme.colorScheme.primary,
    height: Dp = space48,
    contentPadding: PaddingValues = ButtonDefaults.ContentPadding,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(space10),
        contentPadding = contentPadding,
        modifier = Modifier
            .then(modifier)
            .conditional(fillMaxWidth) {
                fillMaxWidth()
            }
            .height(height),
        colors = ButtonDefaults.buttonColors(
            disabledContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            containerColor = color,
        ),
        enabled = isEnabled,
    ) {
        iconRes?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
            )
        }
        Text(
            overflow = TextOverflow.Ellipsis,
            style = TypographyKit.Label.medium,
            maxLines = 1,
            text = text,
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewDefaultButton(
    @PreviewParameter(DefaultButtonPreviewProvider::class) params: DefaultButtonParams
) {
    EventifyTheme {
        DefaultButton(
            text = "Text",
            onClick = {},
            isEnabled = params.enabled,
        )
    }
}

private data class DefaultButtonParams(
    val enabled: Boolean,
    val isLoading: Boolean,
)


private class DefaultButtonPreviewProvider : PreviewParameterProvider<DefaultButtonParams> {
    override val values: Sequence<DefaultButtonParams>
        get() = sequenceOf(
            DefaultButtonParams(
                enabled = true,
                isLoading = false
            ),
            DefaultButtonParams(
                enabled = false,
                isLoading = false,
            ),
            DefaultButtonParams(
                enabled = true,
                isLoading = true,
            ),
        )
}
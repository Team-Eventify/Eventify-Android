package uikit.components.buttons

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme

@Composable
fun UpdateStatusButton(
    text: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(20.dp, 8.dp),
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
    ) {
        Text(
            text = text,
        )
    }
}

@Preview
@Composable
private fun PreviewUpdateStatusButtonDark() {
    EventifyTheme(darkTheme = true) {
        UpdateStatusButton(
            text = "Text",
            onClick = {}
        )
    }
}

@Preview
@Composable
private fun PreviewUpdateStatusButtonLight() {
    EventifyTheme(darkTheme = false) {
        UpdateStatusButton(
            text = "Text",
            onClick = {}
        )
    }
}
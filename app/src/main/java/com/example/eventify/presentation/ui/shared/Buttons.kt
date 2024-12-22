package com.example.eventify.presentation.ui.shared

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.presentation.ui.theme.EventifyTheme


@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(40.dp, 12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled,
        content = content
    )
}

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

@Preview("UpdateStatusButtonDark")
@Composable
private fun UpdateStatusButtonDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            UpdateStatusButton(
                text = "Text",
                onClick = {}
            )
        }
    }
}


//@Composable
//fun FavouriteButton(
//    isChecked: Boolean,
//    onCheckChanged: (Boolean) -> Unit
//) {
//    IconToggleButton(
//        checked = isChecked,
//        onCheckedChange = onCheckChanged
//    ){
//        Icon(painter = , contentDescription = )
//    }
//}
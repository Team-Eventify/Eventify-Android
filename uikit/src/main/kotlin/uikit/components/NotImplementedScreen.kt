package uikit.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.uikit.R as UiKitR
import uikit.EventifyTheme

@Composable
fun NotImplementedScreen() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        BorderedImage(
            painter = painterResource(id = UiKitR.drawable.round_watch_later_24),
            contentDescription = null,
        )

        Text(
            text = stringResource(id = UiKitR.string.not_implemented_screen_message),
            color = MaterialTheme.colorScheme.onSurface,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview(name = "NotImplementedScreenDark")
@Composable
private fun PreviewNotImplementedScreenDarkPreview() {
    EventifyTheme(darkTheme = true) {
        Surface {
            NotImplementedScreen()
        }
    }
}

@Preview(name = "NotImplementedScreenLight")
@Composable
private fun PreviewNotImplementedScreenLightPreview() {
    EventifyTheme {
        Surface {
            NotImplementedScreen()
        }
    }
}
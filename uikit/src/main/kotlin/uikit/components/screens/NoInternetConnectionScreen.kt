package uikit.components.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uikit.EventifyTheme
import uikit.components.BorderedImage
import com.example.eventify.uikit.R as UiKitR


@Composable
fun NoInternetConnectionScreen(
    modifier: Modifier = Modifier,
) {
    BaseInfoScreen(
        title = stringResource(UiKitR.string.default_offline_title),
        description = stringResource(UiKitR.string.default_offline_message),
        icon = {
            BorderedImage(painter = painterResource(UiKitR.drawable.ic_no_internet))
        },
        button = {

        },
        modifier = modifier
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NoInternetConnectionScreenPreview() {
    EventifyTheme {
        NoInternetConnectionScreen()
    }
}
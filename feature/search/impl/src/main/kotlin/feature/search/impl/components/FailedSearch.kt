package feature.search.impl.components

import android.content.res.Configuration
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import uikit.components.BorderedImage
import uikit.components.buttons.PrimaryActionButton
import uikit.components.screens.BaseInfoScreen
import com.example.eventify.uikit.R as UiKitR

@Composable
fun FailedSearch(
    message: String?,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BaseInfoScreen(
        icon = {
            BorderedImage(
                painter = painterResource(UiKitR.drawable.ic_error),
                contentDescription = null,
            )
        },
        button = {
            PrimaryActionButton(
                onClick = onRefresh,
                text = stringResource(UiKitR.string.update)
            )
        },
        title = stringResource(UiKitR.string.failed_to_load_data),
        description = message,
        modifier = modifier,
    )
}

@Preview(name = "FailedSearch", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewFailedSearch() {
    FailedSearch(
        message = "Some text",
        onRefresh = {},
    )
}
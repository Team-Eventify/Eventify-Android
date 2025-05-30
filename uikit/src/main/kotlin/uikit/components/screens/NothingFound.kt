package uikit.components.screens

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.uikit.R
import uikit.components.BorderedImage
import uikit.components.buttons.PrimaryActionButton


@Composable
fun NothingFound(
    modifier: Modifier = Modifier,
    actionButton: Pair<String, () -> Unit>? = null,
) {
    BaseInfoScreen(
        modifier = Modifier
            .then(modifier),
        title = stringResource(R.string.nothing_fount),
        icon = {
            BorderedImage(
                painter = painterResource(R.drawable.rounded_search_off_24),
            )
        },
        button = {
            actionButton?.let {
                PrimaryActionButton(
                    text = actionButton.first,
                    onClick = actionButton.second,
                )
            }
        }
    )
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun NothingFoundPreview() {
    NothingFound()
}
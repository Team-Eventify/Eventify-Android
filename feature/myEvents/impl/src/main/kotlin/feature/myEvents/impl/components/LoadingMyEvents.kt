package feature.myEvents.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.components.SkeletonBox


@Composable
fun LoadingMyEvents() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        repeat(6) {
            SkeletonBox(
                shimmer = true,
                height = 140.dp,
            )
        }

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingMyEvents() {
    EventifyTheme {
        LoadingMyEvents()
    }
}
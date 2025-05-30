package feature.search.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.components.SkeletonBox
import uikit.space18

@Composable
fun InitialSearch() {
    Column(
        verticalArrangement = Arrangement.spacedBy(space18),
        modifier = Modifier
            .fillMaxSize()
    ) {
        repeat(8) {
            SkeletonBox(
                shimmer = true,
                height = 160.dp,
            )
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun InitialSearchPreview() {
    EventifyTheme {
        InitialSearch()
    }
}
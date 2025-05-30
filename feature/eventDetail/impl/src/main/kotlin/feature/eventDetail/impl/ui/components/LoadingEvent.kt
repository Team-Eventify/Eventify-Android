package feature.eventDetail.impl.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.EventifyTheme
import uikit.LocalDimentions
import uikit.components.shimmer
import uikit.space10
import uikit.space16
import uikit.space8
import uikit.components.SkeletonBox


@Composable
fun LoadingEvent() {
    val dims = LocalDimentions.current

    Column(
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(space10, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .shimmer()
    ) {
        SkeletonBox(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = space8)
                .padding(top = space16)
                .height(200.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(space10, Alignment.Top),
            modifier = Modifier
                .padding(dims.windowPaddings)
        ) {
            SkeletonBox(height = 50.dp)

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(3) {
                    SkeletonBox(width = 80.dp, height = 30.dp)
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                modifier = Modifier.fillMaxWidth()
            ) {
                repeat(2) {
                    SkeletonBox(width = 130.dp, height = 30.dp)
                }
            }

            repeat(6) {
                SkeletonBox(
                    height = 50.dp,
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally)
            ) {
                SkeletonBox(
                    modifier = Modifier
                        .height(50.dp)
                        .aspectRatio(1f)

                )
                SkeletonBox(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                )
            }

            SkeletonBox(height = 60.dp)
        }
    }
}

@Preview(name = "LoadingEvent", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(name = "LoadingEvent", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLoadingEvent() {
    EventifyTheme {
        LoadingEvent()
    }
}
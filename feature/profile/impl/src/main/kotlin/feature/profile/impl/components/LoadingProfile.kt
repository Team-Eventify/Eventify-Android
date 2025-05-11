package feature.profile.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.space20
import uikit.LocalDimentions
import uikit.components.shimmer

@Composable
fun LoadingProfile(
    modifier: Modifier = Modifier
) {
    val dims = LocalDimentions.current

    Column(
        verticalArrangement = Arrangement.spacedBy(space20, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .padding(dims.windowPaddings)
            .shimmer()
            .then(modifier),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(space20))
        )

        repeat(7) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(space20))
            )
        }
    }
}

@Preview(name = "LoadingProfile Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LoadingProfile Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingProfile() {
    LoadingProfile()
}
package feature.myEvents.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import uikit.components.shimmer
import uikit.EventifyTheme

@Composable
fun LoadingMyEvents() {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top),
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .shimmer(
                showShimmer = true
            )
    ) {
        repeat(6) {
            LoadingEvent()
            Spacer(Modifier.height(20.dp))
        }
    }
}

@Composable
private fun LoadingEvent() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.Top)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(.7f)
                .height(30.dp)
                .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start)
        ) {
            repeat(3) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.15f)
                        .height(30.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(60.dp))
                )
            }
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingMyEvents() {
    EventifyTheme(dynamicColor = true) {
        LoadingMyEvents()
    }
}
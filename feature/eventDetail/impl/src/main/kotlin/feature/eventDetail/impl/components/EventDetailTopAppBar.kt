package feature.eventDetail.impl.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.sp
import uikit.EventifyTheme
import com.example.eventify.uikit.R as UiKitR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailTopAppBar(
    title: String,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors =  TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        modifier = modifier,
        title = {
            Text(
                text = title,
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    painter = painterResource(UiKitR.drawable.ic_chevron_right),
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*Share*/ }
            ) {
                Icon(
                    painter = painterResource(UiKitR.drawable.ic_arrow_turn_up_right),
                    contentDescription = ""
                )
            }

        }
    )
}

@Preview(name = "EventDetailTopAppBarDark")
@Composable
private fun PreviewEventDetailTopAppBarDark() {
    EventifyTheme(darkTheme = true) {
        EventDetailTopAppBar(
            title = LoremIpsum(3).values.joinToString(),
            onNavigateUp = {}
        )
    }
}
package uikit.components

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
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.uikit.R as UiKitR


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTopAppBar(
    title: String,
    onNavigateUp: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Transparent
        ),
        title = {
            Text(text = title)
        },
        navigationIcon = onNavigateUp?.let {
            {
                IconButton(
                    onClick = it
                ) {
                    Icon(
                        painter = painterResource(UiKitR.drawable.ic_chevron_right),
                        contentDescription = ""
                    )
                }
            }
        } ?: {},
        modifier = Modifier.then(modifier)
    )
}

@Preview(name = "DefaultTopAppBar")
@Composable
private fun PreviewDefaultTopAppBar() {
    DefaultTopAppBar(
        title = "Title"
    )
}
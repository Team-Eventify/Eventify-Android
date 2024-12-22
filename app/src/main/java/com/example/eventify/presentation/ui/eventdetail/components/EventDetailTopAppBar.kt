package com.example.eventify.presentation.ui.eventdetail.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailTopAppBar(
    title: String,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = onNavigateUp
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_chevron_right),
                    contentDescription = ""
                )
            }
        },
        actions = {
            IconButton(
                onClick = { /*Share*/ }
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_turn_up_right),
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
            title = "",
            onNavigateUp = {}
        )
    }
}
package com.example.eventify.presentation.ui.account.profileedit.components

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
import com.example.eventify.presentation.ui.common.shimmer
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.LocalDimentions
import com.example.eventify.presentation.ui.theme.space10
import com.example.eventify.presentation.ui.theme.space20

@Composable
fun LoadingProfileEdit(
    modifier: Modifier = Modifier
) {
    val dims = LocalDimentions.current

    Column(
        verticalArrangement = Arrangement.spacedBy(space20, Alignment.Top),
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(dims.windowPaddings)
            .shimmer()
            .then(modifier),
    ) {
        repeat(7) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(.5f)
                        .height(30.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(space20))
                )
                Spacer(Modifier.height(space10))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                        .background(MaterialTheme.colorScheme.surface, RoundedCornerShape(space20))
                )
            }
        }
    }
}

@Preview(name = "LoadingProfileEdit Dark", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview(name = "LoadingProfileEdit Light", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewLoadingProfileEdit() {
    EventifyTheme {
        LoadingProfileEdit()
    }
}
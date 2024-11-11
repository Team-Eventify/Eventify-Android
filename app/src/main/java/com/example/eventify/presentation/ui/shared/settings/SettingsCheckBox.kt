package com.example.eventify.presentation.ui.shared.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun SettingsCheckBox(
    title: String,
    checked: Boolean,
    description: String? = null,
    onCheckedChange: ((Boolean) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckedChange
        )

        Column(

        ) {
            Text(text = title)
            description?.let {
                Text(text = it)
            }
        }
    }
}

@Preview(name = "SettingsCheckBox", showSystemUi = true, showBackground = true)
@Composable
private fun PreviewSettingsCheckBox() {
    SettingsCheckBox(
        title = "Dark mode",
        description = "Use dark mode",
        checked = true,
    )
}
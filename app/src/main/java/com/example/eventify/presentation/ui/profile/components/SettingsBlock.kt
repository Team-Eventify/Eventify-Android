package com.example.eventify.presentation.ui.profile.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun SettingsBlock(
    modifier: Modifier = Modifier,
    surfaceContainer: Color = MaterialTheme.colorScheme.surfaceContainer,
    content: @Composable() (ColumnScope.() -> Unit)
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = surfaceContainer
        ),
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
        ) {
            content()
        }
    }

}


@Composable
fun BaseSettingsItem(
    content: @Composable() () -> Unit,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    onClick: (() -> Unit)? = null
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick?.invoke()
            }
            .padding(12.dp)

    ) {
        leadingIcon?.invoke()
        content()
        trailingIcon?.invoke()
    }
}

@Composable
fun SettingsItemText(
    text: String,
    color: Color = MaterialTheme.colorScheme.onSurface
) {
    Text(text = text, color = color)
}

@Composable
fun SupportingSettingsItemText(
    text: String
) {
    AnnotationText(
        text = text
    )
}

@Composable
fun NavigationSettingsItem(
    text: String,
    supportingText: String? = null,
    onClick: (() -> Unit)? = null
) {
    BaseSettingsItem(
        content = {
            Column {
                SettingsItemText(text = text)
                supportingText?.let {
                    SupportingSettingsItemText(text = it)
                }
            }
        },
        onClick = onClick,
        trailingIcon = {
            Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = null)
        }
    )
}

@Composable
fun ActionSettingsItem(
    text: String,
    onClick: (() -> Unit)?
) {
    BaseSettingsItem(
        content = {
            SettingsItemText(text)
        },
        onClick = onClick
    )
}

@Composable
fun ImportantActionSettingsItem(
    text: String,
    onClick: (() -> Unit)?
) {
    BaseSettingsItem(
        content = {
            SettingsItemText(text, color = MaterialTheme.colorScheme.error)
        },
        onClick = onClick
    )
}


@Preview(name = "SettingsBlock")
@Composable
private fun PreviewSettingsBlock() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            SettingsBlock{
                NavigationSettingsItem("Navigation item")
                HorizontalDivider()
                ActionSettingsItem("Action item", onClick = null)
                HorizontalDivider()
                NavigationSettingsItem("Navigation item", supportingText = "With supporting text")
            }
        }
    }
}
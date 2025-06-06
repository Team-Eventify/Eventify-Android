package uikit.components

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.eventify.uikit.R
import uikit.TypographyKit

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
            .then(modifier)
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
fun NavigationSettingsItem(
    text: String,
    supportingText: String? = null,
    onClick: (() -> Unit)? = null
) {
    BaseSettingsItem(
        content = {
            Column {
                Text(
                    text = text,
                    style = TypographyKit.Body.regular
                )
                supportingText?.let {
                    Text(
                        text = supportingText,
                        style = TypographyKit.Body.small,
                    )
                }
            }
        },
        onClick = onClick,
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = null,
            )
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
            Text(
                text = text,
                style = TypographyKit.Body.regular,
            )
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
            Text(
                text = text,
                style = TypographyKit.Body.regular,
                color = MaterialTheme.colorScheme.error,
            )
        },
        onClick = onClick
    )
}

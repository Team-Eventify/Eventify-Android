package feature.register.impl.components

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uikit.EventifyTheme
import uikit.space6
import com.example.eventify.uikit.R as UiKitR

private const val BottomLeftId = "BottomLeftId"
private const val BottomRightId = "BottomRightId"

@Composable
fun OtpKeyboard(
    numberAction: (Int) -> Unit,
    onDelete: () -> Unit,
    onSecondaryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val keys = listOf(
        listOf(1, 4, 7, BottomLeftId),
        listOf(2, 5, 8, 0),
        listOf(3, 6, 9, BottomRightId),
    )

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(space6, Alignment.CenterHorizontally),
        modifier = Modifier
            .then(modifier)
    ) {
        keys.forEach {
            Column(
                verticalArrangement = Arrangement.spacedBy(space6),
            ) {
                it.forEach { item ->
                    when (item) {
                        is Int -> {
                            OtpKeyboardButton(
                                text = item.toString(),
                                onClick = {
                                    numberAction(item)
                                }
                            )
                        }
                        BottomLeftId -> {
                            TextButton(
                                onClick = onSecondaryAction,
                                modifier = Modifier
                                    .size(94.dp, 70.dp)

                            ) {
                                Text(
                                    text = "Назад"
                                )
                            }
                        }
                        BottomRightId -> {
                            OtpActionButton(
                                iconRes = UiKitR.drawable.baseline_backspace_24,
                                onClick = onDelete,
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun OtpKeyboardButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(94.dp, 70.dp)
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable {
                onClick()
            }
    ) {
        Text(
            text = text,
            fontSize = 20.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .then(modifier)
        )
    }
}

@Composable
fun OtpActionButton(
    @DrawableRes iconRes: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(94.dp, 70.dp)
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .then(modifier)
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewOtpKeyboard() {
    EventifyTheme {
        OtpKeyboard(
            modifier = Modifier
                .fillMaxWidth(),
            numberAction = {},
            onDelete = {},
            onSecondaryAction = {}
        )
    }
}
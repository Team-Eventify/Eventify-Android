package feature.profileEdit.impl.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uikit.EventifyTheme
import com.example.eventify.uikit.R
import uikit.TypographyKit
import com.example.eventify.core.common.R as CommonR
import com.example.eventify.feature.profileEdit.impl.R as ProfileR


@Composable
fun DeleteAccountDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(14.dp),
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.round_delete_forever_24),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        },
        title = {
            Text(
                text = context.getString(ProfileR.string.delete_account_title),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        text = {
            Text(
                text = context.getString(ProfileR.string.delete_account_description),
                style = TypographyKit.Body.regular,
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmation,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(
                    text = stringResource(CommonR.string.delete_action),
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(
                    text = context.getString(CommonR.string.cancel),
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        },
        modifier = modifier
    )
}

@Preview(name = "DeleteAccountDialog")
@Composable
private fun PreviewDeleteAccountDialog() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            DeleteAccountDialog(
                onConfirmation = {},
                onDismissRequest = {}
            )
        }
    }
}
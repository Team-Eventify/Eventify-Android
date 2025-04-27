package com.example.eventify.presentation.ui.account.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun LogOutDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(14.dp),
        icon = {
               Icon(
                   painter = painterResource(id = R.drawable.round_logout_24),
                   contentDescription = null,
                   tint = MaterialTheme.colorScheme.primary,
               )
        },
        title = {
            Text(
                text = "Вы действительно хотите выйти из аккаунта?",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmation
            ) {
                Text(text = "Выйти", color = MaterialTheme.colorScheme.error)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissRequest) {
                Text(text = "Отмена", color = MaterialTheme.colorScheme.onSurface)
            }
        },
        modifier = modifier
    )
}

@Preview(name = "LogOutDialog")
@Composable
private fun PreviewLogOutDialog() {
    EventifyTheme(
        darkTheme = true
    ) {
        Surface {
            LogOutDialog(
                onDismissRequest = {},
                onConfirmation = {}
            )
        }
    }
}
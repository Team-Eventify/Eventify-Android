package com.example.eventify.presentation.ui.profile.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.ui.shared.AnnotationText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun DeleteAccountDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        shape = RoundedCornerShape(14.dp),
        icon = {
            Icon(painter = painterResource(id = R.drawable.round_delete_forever_24), contentDescription = null)
        },
        title = {
            Text(
                text = "Вы действительно хотите удалить аккаунт?",
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        },
        text = {
            AnnotationText(text = "Аккаунт будет удален перманентно. Вы не сможете его воссатновить")
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmation,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.error),
                shape = RoundedCornerShape(14.dp)
            ) {
                Text(text = "Удалить", color = MaterialTheme.colorScheme.error)
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
package com.example.eventify.presentation.ui.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogOutDialog(
    onDismissRequest: () -> Unit,
    onLogOut: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,

    ){
        Card(

        ) {
            Column(
                modifier = Modifier
                    .padding(25.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Вы действительно хотите выйти из аккаунта?",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(
                        onClick = onDismissRequest
                    ) {
                        Text(text = "Назад")
                    }
                    TextButton(
                        onClick = onLogOut
                    ) {
                        Text(
                            text = "Выйти",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                }

            }
        }
    }
}

@Preview(name = "LogOutDialog", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewLogOutDialog() {
    LogOutDialog(
        onDismissRequest = {},
        onLogOut = {}
    )
}
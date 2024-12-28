package com.example.eventify.presentation.ui.account.profileedit.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.presentation.ui.shared.ErrorInputText
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun ProfileEditInput(
    text: String,
    onChange: (String) -> Unit,
    isError: Boolean = false,
    supportingText: String? = null,
    label: String? = null,
    placeholder: String? = null,
    prefix: String? = null,
    singleLine: Boolean = true,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChange,
        isError = isError,
        singleLine = singleLine,
        label = label?.let { { Text(it) } },
        placeholder = { Text(placeholder ?: "", fontSize = 17.sp, fontWeight = FontWeight.Normal) },
        prefix = { Text(prefix ?: "") },
        supportingText = {
            supportingText?.let {
                ErrorInputText(
                    text = it
                )
            }
        },
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .composed { modifier }
    )
}

@Preview(name = "ProfileEditInput Default Dark")
@Composable
private fun PreviewProfileEditInputDefaultDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ProfileEditInput(
                text = "Ivan",
                onChange = {}
            )
        }
    }
}

@Preview(name = "ProfileEditInput Error Dark")
@Composable
private fun PreviewProfileEditInputErrorDark() {
    EventifyTheme(darkTheme = true) {
        Surface {
            ProfileEditInput(
                text = "Ivan",
                onChange = {},
                isError = true,
                supportingText = "Тупое имя"
            )
        }
    }
}

@Preview(name = "ProfileEditInput Default Light")
@Composable
private fun PreviewProfileEditInputDefaultLight() {
    EventifyTheme(darkTheme = false) {
        Surface {
            ProfileEditInput(
                text = "Ivan",
                onChange = {}
            )
        }
    }
}

@Preview(name = "ProfileEditInput Error Light")
@Composable
private fun PreviewProfileEditInputErrorLight() {
    EventifyTheme(darkTheme = false) {
        Surface {
            ProfileEditInput(
                text = "Ivan",
                onChange = {},
                isError = true,
                supportingText = "Тупое имя"
            )
        }
    }
}
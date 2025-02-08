package com.example.eventify.presentation.ui.auth.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.otp.OtpTextField
import com.example.eventify.presentation.ui.theme.EventifyTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationOtpBottomSheet(
    otpValue: String?,
    onSubmit: () -> Unit,
    onChangeOtpValue: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismissRequest,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        sheetMaxWidth = BottomSheetDefaults.SheetMaxWidth,

    ){

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(horizontal = 16.dp)
        ) {
            TitleText(
                text = "Подтверждение почты"
            )
            AnnotationText(
                text = "Мы отправили код подтверждения на вашу почту. Пожалуйста, введите его ниже, чтобы завершить процесс."
            )
            OtpTextField(
                otpCount = 6,
                text = otpValue ?: "",
                onTextChange = onChangeOtpValue
            )

            PrimaryButton(
                onClick = onSubmit,
            ) {
                PrimaryButtonText(text = stringResource(R.string.next))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "RegistrationOtpBottomSheet")
@Composable
private fun PreviewRegistrationOtpBottomSheet() {
    EventifyTheme(darkTheme = true) {
        RegistrationOtpBottomSheet(
            otpValue = "123",
            onChangeOtpValue = {},
            onDismissRequest = {},
            onSubmit = {},
        )
    }
}
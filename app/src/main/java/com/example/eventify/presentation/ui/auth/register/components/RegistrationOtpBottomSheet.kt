package com.example.eventify.presentation.ui.auth.register.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
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
import com.example.eventify.presentation.ui.auth.register.state.OTP_LENGTH
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.PrimaryButtonText
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.common.buttons.PrimaryButton
import com.example.eventify.presentation.ui.common.otp.OtpTextField
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.space32


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
        windowInsets = WindowInsets.ime
    ){

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 10.dp)
        ) {
            TitleText(
                text = stringResource(R.string.email_confirmation)
            )
            AnnotationText(
                text = stringResource(R.string.otp_description)
            )
            OtpTextField(
                otpCount = 6,
                text = otpValue ?: "",
                onTextChange = onChangeOtpValue,
            )

            PrimaryButton(
                onClick = onSubmit,
                enabled = otpValue?.let { it.length == OTP_LENGTH } ?: false,
            ) {
                PrimaryButtonText(text = stringResource(R.string.next))
            }
            Spacer(Modifier.height(space32))
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
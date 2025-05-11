package feature.register.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetDefaults
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import com.example.eventify.R
import com.example.eventify.domain.validation.asOTP
import com.example.eventify.presentation.ui.auth.register.state.OtpState
import com.example.eventify.presentation.ui.common.AnnotationText
import com.example.eventify.presentation.ui.common.TitleText
import com.example.eventify.presentation.ui.common.keyboards.otp.OtpKeyboard
import com.example.eventify.presentation.ui.common.otp.OtpTextField
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.space12
import feature.register.impl.state.OtpState
import uikit.components.otp.OtpTextField


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationOtpBottomSheet(
    otpState: OtpState.ShowOtp,
    onSubmit: (String) -> Unit,
    onChangeOtpValue: (String) -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        containerColor = MaterialTheme.colorScheme.surfaceContainer,
        sheetMaxWidth = BottomSheetDefaults.SheetMaxWidth,
        properties = ModalBottomSheetDefaults.properties(
            shouldDismissOnBackPress = false,
            isFocusable = false,
            securePolicy = SecureFlagPolicy.SecureOn
        )
    ){

        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(bottom = 10.dp)
        ) {
            Image(painter = painterResource(id=R.drawable.email_icon), contentDescription = null)
            Spacer(modifier = Modifier.height(15.dp))
            TitleText(
                text = stringResource(R.string.email_confirmation)
            )
            AnnotationText(
                text = stringResource(R.string.otp_description)
            )
            Text(
                text = otpState.errorMessage.takeIf { !it.isNullOrEmpty() } ?: "",
                color = MaterialTheme.colorScheme.error,
            )
            OtpTextField(
                otpCount = 6,
                text = otpState.otp.value,
                onTextChange = onChangeOtpValue,
                hasError = otpState.hasError && !otpState.errorMessage.isNullOrEmpty(),
                isSuccess = otpState.isSuccess,
                onSubmit = {
                    onSubmit(otpState.otp.value)
                },
                modifier = Modifier
            )

            Spacer(Modifier.height(space12))

            OtpKeyboard(
                modifier = Modifier
                    .fillMaxWidth(),
                numberAction = { char ->
                    otpState.otp.value.plus(char).let {
                        onChangeOtpValue(it)

                        if (it.length == 6)
                            onSubmit(it)
                    }
                },
                onDelete = {
                    onChangeOtpValue(otpState.otp.value.dropLast(1))
                },
                onSecondaryAction = onDismissRequest,
            )
            Spacer(Modifier.height(space12))
            
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(name = "RegistrationOtpBottomSheet Light", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
private fun PreviewRegistrationOtpBottomSheet() {
    EventifyTheme {
        RegistrationOtpBottomSheet(
            otpState = OtpState.ShowOtp(
                otp = "123".asOTP(),
            ),
            onChangeOtpValue = {},
            onDismissRequest = {},
            onSubmit = {},
        )
    }
}
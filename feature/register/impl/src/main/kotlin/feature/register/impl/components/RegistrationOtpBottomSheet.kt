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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import feature.register.impl.state.OtpState
import uikit.components.otp.OtpTextField
import com.example.eventify.uikit.R as UiKitR
import uikit.space12
import uikit.components.TitleText
import uikit.EventifyTheme
import uikit.TypographyKit

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
            Image(painter = painterResource(id=UiKitR.drawable.email_icon), contentDescription = null)
            Spacer(modifier = Modifier.height(15.dp))
            TitleText(
                text = stringResource(UiKitR.string.email_confirmation)
            )
            Text(
                text = stringResource(UiKitR.string.otp_description),
                style = TypographyKit.bodyRegular,
            )
            Text(
                text = otpState.errorMessage.takeIf { !it.isNullOrEmpty() } ?: "",
                color = MaterialTheme.colorScheme.error,
            )
            OtpTextField(
                otpCount = 6,
                text = otpState.otp,
                onTextChange = onChangeOtpValue,
                hasError = otpState.hasError && !otpState.errorMessage.isNullOrEmpty(),
                isSuccess = otpState.isSuccess,
                onSubmit = {
                    onSubmit(otpState.otp)
                },
                modifier = Modifier
            )

            Spacer(Modifier.height(space12))

            OtpKeyboard(
                modifier = Modifier
                    .fillMaxWidth(),
                numberAction = { char ->
                    otpState.otp.plus(char).let {
                        onChangeOtpValue(it)

                        if (it.length == 6)
                            onSubmit(it)
                    }
                },
                onDelete = {
                    onChangeOtpValue(otpState.otp.dropLast(1))
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
                otp = "123",
            ),
            onChangeOtpValue = {},
            onDismissRequest = {},
            onSubmit = {},
        )
    }
}
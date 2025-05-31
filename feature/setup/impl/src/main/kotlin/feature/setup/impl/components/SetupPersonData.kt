package feature.setup.impl.components

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import feature.setup.impl.state.MainSetupUiState
import feature.setup.impl.state.SetUpListener
import uikit.LocalDimentions
import com.example.eventify.uikit.R as UiKitR
import uikit.space20
import uikit.EventifyTheme
import uikit.TypographyKit
import uikit.components.buttons.PrimaryButtonWithLoader


@Composable
fun SetupPersonData(
    state: MainSetupUiState,
    actions: SetUpListener,
) {
    val dimmentions = LocalDimentions.current
    val isEnabledFlowNext = remember(state) {
        state.userDataState.lastName.isNotEmpty() && state.userDataState.lastName.isNotEmpty()
    }

    Column(
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(dimmentions.windowPaddings)
    ) {
        Text(
            text = stringResource(UiKitR.string.choose_categories_that_interest_you),
            style = TypographyKit.Heading.xlarge,
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(space20)
        ) {
            OutlinedTextField(
                value = state.userDataState.firstName,
                onValueChange = actions::onChangeFirstName,
                modifier = Modifier
                    .fillMaxWidth()
            )

            OutlinedTextField(
                value = state.userDataState.lastName,
                onValueChange = actions::onChangeLastName,
                modifier = Modifier
                    .fillMaxWidth()

            )
        }

        PrimaryButtonWithLoader(
            text = stringResource(UiKitR.string.next),
            onClick = actions::flowNext,
            isEnabled = isEnabledFlowNext,
            isLoading = false,
        )

    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun SetupPersonDataPreview() {
    EventifyTheme {
        SetupPersonData(
            state = MainSetupUiState(),
            actions = object : SetUpListener {
                override fun selectCategory(categoryId: String, selected: Boolean) = Unit
                override fun onChangeFirstName(value: String) = Unit
                override fun onChangeLastName(value: String) = Unit
                override fun flowNext() = Unit
                override fun flowBack() = Unit
            }
        )
    }
}
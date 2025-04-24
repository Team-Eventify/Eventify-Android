package com.example.eventify.presentation.ui.common.topBar

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.eventify.R
import com.example.eventify.presentation.ui.theme.EventifyTheme
import com.example.eventify.presentation.ui.theme.space20
import com.example.eventify.presentation.ui.theme.space4


@Composable
fun SearchTopBar(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onLeadingIconClick: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
) {
    Box(
        modifier = Modifier
    ) {
        SearchInputField(
            value = value,
            onLeadingIconClick = onLeadingIconClick,
            onTrailingIconClick = onTrailingIconClick,
            onValueChanged = onValueChanged,
            onSearch = onSearch,
            modifier = modifier
                .align(Alignment.Center)
                .fillMaxWidth()
        )
    }


}

@Composable
fun SearchInputField(
    value: String,
    onValueChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
    onLeadingIconClick: (() -> Unit)? = null,
    onTrailingIconClick: (() -> Unit)? = null,
    onSearch: (() -> Unit)? = null,
) {
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    TextField(
        value = value,
        onValueChange = onValueChanged,
        placeholder = {
            Text(
                text = stringResource(R.string.search),
            )
        },
        leadingIcon = {
            IconButton(
                onClick = onLeadingIconClick ?: {},
                enabled = onLeadingIconClick != null,
                modifier = Modifier.offset(x = space4)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_search),
                    contentDescription = null,
                )
            }
        },
        trailingIcon = {
            IconButton(
                onClick = onTrailingIconClick ?: {},
                enabled = onTrailingIconClick != null && value.isNotEmpty(),
                modifier = Modifier.offset(x = -space4)
            ) {
                Icon(
                    painter = painterResource(R.drawable.round_close_24),
                    contentDescription = null,
                )
            }
        },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            focusedTextColor = MaterialTheme.colorScheme.onSecondary,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onSecondary,
            focusedTrailingIconColor = MaterialTheme.colorScheme.onSecondary,
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            focusManager.clearFocus(force = true)
        }),
        singleLine = true,
        shape = RoundedCornerShape(space20),
        modifier = Modifier
            .focusRequester(focusRequester)
            .then(modifier)
    )
}




@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SearchTopBarPreview() {
    EventifyTheme {
        SearchTopBar(
            value = "value",
            onValueChanged = {}
        )
    }
}
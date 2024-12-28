package com.example.eventify.presentation.ui.account.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.models.UserShortInfo
import com.example.eventify.presentation.ui.theme.EventifyTheme

@Composable
fun UserProfilePanel(
    firstName: String?,
    lastName: String?,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val profileName = remember(firstName, lastName) {
        if (!firstName.isNullOrEmpty() || !lastName.isNullOrEmpty())
            "$lastName $firstName"
        else
            context.getString(R.string.empty_profile_name)
    }
    Card(
        modifier = modifier
            .clickable { onClick?.invoke() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,

        )
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
                .padding(18.dp)
        ) {
            Column {
                Text(
                    text = profileName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = stringResource(R.string.edit_profile),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Light
                )
            }
            Icon(painter = painterResource(R.drawable.ic_arrow_right), contentDescription = "arrow right")
        }
    }
}

@Preview(name = "UserProfilePanel")
@Composable
private fun PreviewUserProfilePanel() {
    val lorem = LoremIpsum(2)
        .values
        .toList()
    EventifyTheme {
        UserProfilePanel(
            firstName = lorem[0],
            lastName = lorem[1]
        )
    }
}

@Preview(name = "UserProfilePanel")
@Composable
private fun PreviewUserProfilePanelEmptyName() {
    EventifyTheme {
        UserProfilePanel(
            firstName = null,
            lastName = null
        )
    }
}
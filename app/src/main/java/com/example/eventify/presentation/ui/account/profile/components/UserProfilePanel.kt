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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.eventify.R
import com.example.eventify.presentation.models.UserShortInfo

@Composable
fun UserProfilePanel(
    user: UserShortInfo,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
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
                    text = "${user.lastName} ${user.firstName}",
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

@Preview(name = "UserProfilePanel", showBackground = true, showSystemUi = true)
@Composable
private fun PreviewUserProfilePanel() {
    UserProfilePanel(
        user = UserShortInfo(
            id = "",
            firstName = "Иван",
            lastName = "Иванов",
            middleName = "Иванович",
            email = "ivanov@mail.ru"
        )
    )
}
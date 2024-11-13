package com.example.eventify.presentation.ui.shared

import android.widget.ToggleButton
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable() (RowScope.() -> Unit)
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(10.dp),
        contentPadding = PaddingValues(40.dp, 12.dp),
        modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(),
        enabled = enabled,
        content = content
    )
}


//@Composable
//fun FavouriteButton(
//    isChecked: Boolean,
//    onCheckChanged: (Boolean) -> Unit
//) {
//    IconToggleButton(
//        checked = isChecked,
//        onCheckedChange = onCheckChanged
//    ){
//        Icon(painter = , contentDescription = )
//    }
//}
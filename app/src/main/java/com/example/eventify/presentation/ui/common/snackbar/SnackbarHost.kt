package com.example.eventify.presentation.ui.common.snackbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.eventify.presentation.LocalSnackbarState
import com.example.eventify.presentation.ui.theme.space20

@Composable
fun SnackbarHost(
    modifier: Modifier = Modifier
) {
    val controller = LocalSnackbarState.current
    val message by controller.current.collectAsStateWithLifecycle()

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Companion.TopCenter
    ) {
        AnimatedVisibility(
            visible = (message?.isVisible ?: false),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight }
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight }
            ) + fadeOut()
        ) {
            message?.let { msg ->
                Surface(
                    color = msg.type.backgroundColor,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.Companion
                        .padding(space20)
                        .fillMaxWidth(0.95f)
                ) {
                    Row(
                        modifier = Modifier.Companion
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Companion.CenterVertically
                    ) {
                        msg.type.iconResId?.let {
                            Icon(
                                painterResource(it),
                                contentDescription = null,
                            )
                        }
                        Spacer(modifier = Modifier.Companion.width(8.dp))
                        Text(
                            text = msg.text,
                            color = msg.type.textColor,
                            fontSize = 18.sp,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}
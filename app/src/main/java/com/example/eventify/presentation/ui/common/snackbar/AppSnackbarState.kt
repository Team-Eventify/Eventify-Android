package com.example.eventify.presentation.ui.common.snackbar

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.example.eventify.R
import com.example.eventify.presentation.ui.common.snackbar.SnackbarDurations
import com.example.eventify.presentation.ui.theme.BrandYellow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AppSnackbarState(private val scope: CoroutineScope) {
    private val queue = ArrayDeque<SnackbarMessage>()
    private var job: Job? = null
    private val _current = MutableStateFlow<SnackbarMessage?>(null)
    val current = _current.asStateFlow()

    fun show(
        message: String,
        type: SnackbarType,
        durationMillis: SnackbarDurations = SnackbarDurations.DEFAULT,
        force: Boolean = false
    ) {
        val snackbar = SnackbarMessage(message, type, durationMillis, force)

        if (job?.isActive == true) {
            if (force) {
                job?.cancel()
                queue.clear()
                queue.add(snackbar)
                showNext()
            } else {
                queue.addLast(snackbar)
            }
        } else {
            queue.addLast(snackbar)
            showNext()
        }
    }

    private fun showNext() {
        val next = queue.removeFirstOrNull() ?: return
        _current.value = next
        job = scope.launch {
            delay(next.durationMillis.time)
            _current.update { it?.copy(isVisible = false) }
            delay(300)
            _current.value = null
            showNext()
        }
    }
}


// TODO add icons
// TODO try another way to get colors
sealed class SnackbarType(
    val backgroundColor: Color,
    val textColor: Color,
    @DrawableRes val iconResId: Int? = null,
) {
    object Success : SnackbarType(
        backgroundColor = BrandYellow,
        textColor = Color(0xFFFFFFFF),
        iconResId = R.drawable.ic_error,
    )

    object Error : SnackbarType(
        backgroundColor = Color(0xFFFF8F88),
        textColor = Color(0xFFFFFFFF),
        iconResId = R.drawable.ic_error,
    )

    object Info : SnackbarType(
        backgroundColor = Color(0xFF858591),
        textColor = Color(0xFFFFFFFF),
        iconResId = R.drawable.ic_error,
    )
}

@Stable
data class SnackbarMessage(
    val text: String,
    val type: SnackbarType,
    val durationMillis: SnackbarDurations = SnackbarDurations.DEFAULT,
    val force: Boolean = false,
    val isVisible: Boolean = true,
)

enum class SnackbarDurations(val time: Long) {
    SHORT(1000L),
    DEFAULT(3000L),
    LONG(3500L),
    INFINITE(Long.MAX_VALUE),
}
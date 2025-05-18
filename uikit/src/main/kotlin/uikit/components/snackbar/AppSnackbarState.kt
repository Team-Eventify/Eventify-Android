package uikit.components.snackbar

import androidx.compose.runtime.Stable
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
        style: SnackbarStyle,
        durationMillis: SnackbarDurations = SnackbarDurations.DEFAULT,
        description: String? = null,
        force: Boolean = false
    ) {
        val snackbar = SnackbarMessage(
            title = message,
            description = description,
            style = style,
            durationMillis = durationMillis,
            force = force,
        )

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




@Stable
data class SnackbarMessage(
    val title: String,
    val description: String? = null,
    val style: SnackbarStyle,
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
package core.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import core.common.extentions.isUnauthorized
import core.common.secure.AuthProvider
import core.common.secure.AuthorizeType
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class BaseViewModel : ViewModel() {

    @Inject
    lateinit var authProvider: AuthProvider

    protected fun launchCatching(
        scope: CoroutineScope = viewModelScope,
        catch: ((Throwable) -> Unit)? = null,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(CoroutineExceptionHandler { _, throwable ->
            throwable.printStackTrace()
            // TODO залогирговать

            if (throwable.isUnauthorized()) {
                updateAuthStateToUnauthorized()
            } else {
                catch?.invoke(throwable)
            }
        }) {
            withContext(Dispatchers.IO) {
                block()
            }
        }.also {
            it.invokeOnCompletion {
                // TODO чтото залогировать
            }
        }
    }

    open fun updateAuthStateToUnauthorized() {
        launchCatching {
            authProvider.updateState(AuthorizeType.Unauthorized)
        }
    }
}
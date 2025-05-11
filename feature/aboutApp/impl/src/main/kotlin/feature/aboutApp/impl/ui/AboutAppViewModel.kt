package feature.aboutApp.impl.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import feature.aboutApp.impl.state.AboutAppState
import javax.inject.Inject
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import com.example.eventify.uikit.R as UiKitR

@HiltViewModel
class AboutAppViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _stateFlow: MutableStateFlow<AboutAppState> = MutableStateFlow(AboutAppState())
    val stateFlow: StateFlow<AboutAppState> = _stateFlow
        .onStart { loadData() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            AboutAppState()
        )

    private fun loadData(){
        viewModelScope.launch {
            val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
            val versionName = packageInfo.versionName ?: "Unknown"

            _stateFlow.update { currentState ->
                currentState.copy(
                    versionName = context.getString(UiKitR.string.version, versionName)
                )
            }
        }
    }
}
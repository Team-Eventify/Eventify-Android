package feature.onboarding.impl

import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.SharedStorage
import com.example.eventify.data.storages.StorageKeys
import com.example.eventify.presentation.utils.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    @SharedStorage private val localeStorage: LocaleStorage
) : BaseViewModel() {
    fun finishOnboarding() {
        localeStorage.put(StorageKeys.IS_SHOW_BOARDING, false)
    }
}
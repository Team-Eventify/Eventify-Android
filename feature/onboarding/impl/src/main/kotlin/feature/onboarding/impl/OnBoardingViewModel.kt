package feature.onboarding.impl

import core.common.BaseViewModel
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
package feature.onboarding.impl

import core.common.BaseViewModel
import core.common.storages.LocaleStorage
import core.common.storages.SharedStorage
import core.common.storages.StorageKeys
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
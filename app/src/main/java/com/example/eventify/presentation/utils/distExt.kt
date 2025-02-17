package com.example.eventify.presentation.utils

import com.example.eventify.data.storages.LocaleStorage
import com.example.eventify.data.storages.StorageKeys


fun LocaleStorage.isShowOnboarding(): Boolean {
    return this.getBoolean(StorageKeys.IS_SHOW_BOARDING, defaultValue = true) == true
}

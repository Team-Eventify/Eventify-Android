package core.common.extentions

import core.common.storages.LocaleStorage
import core.common.storages.StorageKeys

fun LocaleStorage.isShowOnboarding(): Boolean {
    return this.getBoolean(StorageKeys.IS_SHOW_BOARDING, defaultValue = true) == true
}
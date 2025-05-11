package feature.profileEdit.api

import core.common.navigation.AccountRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val ProfileEditPath = AccountRoot.child("edit")

interface ProfileEditEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfileEditPath
}


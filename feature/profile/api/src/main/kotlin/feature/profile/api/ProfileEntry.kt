package feature.profile.api

import core.common.navigation.AccountRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val ProfilePath = AccountRoot.child("profile")


interface ProfileEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfilePath
}



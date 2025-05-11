package feature.profile.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry

val AccountRootPath = BaseDestination("account")

val ProfilePath = AccountRootPath.child("profile")


interface ProfileEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfilePath
}



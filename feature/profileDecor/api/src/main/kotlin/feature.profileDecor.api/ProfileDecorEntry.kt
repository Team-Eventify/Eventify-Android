package feature.profileDecor.api

import core.common.navigation.AccountRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry

val ProfileDecorPath = AccountRoot.child("decor")

interface ProfileDecorEntry: ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ProfileDecorPath
}

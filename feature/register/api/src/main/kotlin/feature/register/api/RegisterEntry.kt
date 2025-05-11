package feature.register.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val RegisterPath = AuthRootPath.child("register")

interface RegisterEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = RegisterPath
}


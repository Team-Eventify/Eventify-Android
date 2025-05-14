package feature.register.api

import core.common.navigation.AuthRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val RegisterPath = AuthRoot.child("register")

interface RegisterEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = RegisterPath
}


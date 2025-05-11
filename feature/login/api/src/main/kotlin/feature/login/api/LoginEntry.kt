package feature.login.api

import core.common.navigation.AuthRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry

val LoginPath = AuthRoot.child("login")

interface LoginEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = LoginPath
}
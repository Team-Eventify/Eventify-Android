package feature.login.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry

val AuthRootPath = BaseDestination("auth")

val LoginPath = AuthRootPath.child("login")

interface LoginEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = LoginPath
}
package feature.setup.api

import core.common.navigation.AuthRoot
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val SetUpPath = AuthRoot.child("setup")

interface SetUpEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = SetUpPath
}


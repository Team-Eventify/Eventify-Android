package feature.onboarding.api

import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry

val OnBoardingPath = BaseDestination("onboarding")

interface OnBoardingEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = OnBoardingPath
}


package feature.resetPassword.api

import core.common.navigation.ARG_SHARED_EMAIL
import core.common.navigation.BaseDestination
import core.common.navigation.ComposableFeatureEntry


val ResetPasswordPath = AuthRootPath.child(
    "reset_password",
    ARG_SHARED_EMAIL
)

interface ResetPasswordEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = ResetPasswordPath
}


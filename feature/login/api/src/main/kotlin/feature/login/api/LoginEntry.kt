package feature.login.api

val AuthRootPath = BaseDestination("auth")

val LoginPath = AuthRootPath.child("login")

interface LoginEntry : ComposableFeatureEntry {
    override val destination: BaseDestination
        get() = LoginPath
}
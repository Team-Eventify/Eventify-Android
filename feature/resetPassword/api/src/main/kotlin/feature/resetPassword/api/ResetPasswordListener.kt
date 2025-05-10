package feature.resetPassword.api

interface ResetPasswordListener {
    fun submit()
    fun changeEmail(email: String)
}
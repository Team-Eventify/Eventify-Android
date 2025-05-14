package feature.login.api

interface LoginListener {
    fun onChangeLogin(login: String): Unit
    fun onChangePassword(password: String): Unit
    fun login(login: String, password: String): Unit
    fun navigateToRegister(): Unit
    fun navigateToResetPassword(sharedEmail: String? = null): Unit
}
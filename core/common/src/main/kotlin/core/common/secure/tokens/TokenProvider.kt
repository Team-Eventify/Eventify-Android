package core.common.secure.tokens

interface TokenProvider {
    fun getAccessToken(): String?
    fun setAccessToken(token: String): Unit
    fun getRefreshToken(): String?
    fun setRefreshToken(token: String): Unit
    fun getUserId(): String?
    fun setUserId(value: String)
    fun clear(): Unit
    fun isValidData(): Boolean

}
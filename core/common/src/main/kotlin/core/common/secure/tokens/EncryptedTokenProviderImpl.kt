package core.common.secure.tokens


import core.common.storages.EncryptedStorage
import core.common.storages.LocaleStorage
import core.common.storages.StorageKeys
import javax.inject.Inject


class EncryptedTokenProviderImpl @Inject constructor(
    @EncryptedStorage private val localeStorage: LocaleStorage
): TokenProvider {

    override fun getAccessToken(): String? = localeStorage.getString(StorageKeys.ACCESS_TOKEN, null)

    override fun setAccessToken(token: String) {
        localeStorage.put(StorageKeys.ACCESS_TOKEN, token)
    }

    override fun getRefreshToken(): String? = localeStorage.getString(StorageKeys.REFRESH_TOKEN, null)

    override fun setRefreshToken(token: String) {
        localeStorage.put(StorageKeys.REFRESH_TOKEN, token)
    }

    override fun getUserId(): String? = localeStorage.getString(StorageKeys.USER_ID, null)

    override fun setUserId(value: String) {
        localeStorage.put(StorageKeys.USER_ID, value)
    }

    override fun clear() {
        localeStorage.apply {
            remove(StorageKeys.ACCESS_TOKEN)
            remove(StorageKeys.REFRESH_TOKEN)
            remove(StorageKeys.USER_ID)
        }
    }

    override fun isValidData(): Boolean {
        getAccessToken()?.let { decodeToken(it) } ?: return false
        getRefreshToken() ?: return false
        getUserId() ?: return false
        return true
    }
    private fun decodeToken(token: String): DecodedJWT? {
        return try {
            JWT.decode(token)
        } catch (e: Exception){
            // TODO write logs
            null
        }
    }
}
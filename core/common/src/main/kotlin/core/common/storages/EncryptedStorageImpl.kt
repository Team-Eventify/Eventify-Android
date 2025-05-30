package core.common.storages

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.getValue

class EncryptedStorageImpl @Inject constructor(
    @ApplicationContext context: Context,
    private val storageName: String,
    private val gson: Gson,
) : LocaleStorage {
    private val masterKeyAlias by lazy {
        MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    }

    private val sharedPreferences by lazy {
        EncryptedSharedPreferences.create(
            storageName,
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }

    override fun put(key: String, value: Any) {
        sharedPreferences.edit(commit = true) {
            when (value) {
                is String -> putString(key, value)
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Float -> putFloat(key, value)
                is Long -> putLong(key, value)
                else -> putString(key, gson.toJson(value))
            }
        }
    }

    override fun putMany(values: Map<String, Any>) {
        values.forEach { (key, value) ->
            put(key, value)
        }
    }

    override fun remove(key: String) {
        sharedPreferences.edit(commit = true) { remove(key) }
    }

    override fun removeAll() {
        sharedPreferences.edit(commit = true) { removeAll() }
    }

    override fun getString(key: String, defaultValue: String?): String? {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    override fun getFloat(key: String, defaultValue: Float): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    override fun <T> get(key: String, clazz: Class<T>): T? {
        return try {
            val json = sharedPreferences.getString(key, "").orEmpty()
            gson.fromJson(json, clazz)
        } catch (e: Exception) {
            null
        }
    }

    override fun getAll(): Map<String, *> {
        return sharedPreferences.all
    }
}
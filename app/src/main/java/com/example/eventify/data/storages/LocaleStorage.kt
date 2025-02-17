package com.example.eventify.data.storages

interface LocaleStorage {
    fun put(key: String, value: Any)
    fun putMany(values: Map<String, Any>)
    fun remove(key: String)
    fun removeAll()
    fun getString(key: String, defaultValue: String? = null): String?
    fun getInt(key: String, defaultValue: Int): Int
    fun getBoolean(key: String, defaultValue: Boolean): Boolean
    fun getFloat(key: String, defaultValue: Float): Float
    fun getLong(key: String, defaultValue: Long): Long
    fun <T> get(key: String, clazz: Class<T>): T?
    fun getAll(): Map<String, *>
}
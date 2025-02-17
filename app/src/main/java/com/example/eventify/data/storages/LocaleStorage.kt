package com.example.eventify.data.storages

interface LocaleStorage {
    fun put(key: String, value: Any)
    fun putMany(values: Map<String, Any>)
    fun remove(key: String)
    fun removeAll()
    fun getString(key: String, defaultValue: String = ""): String
    fun getInt(key: String, defaultValue: Int = 0): Int
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean
    fun getFloat(key: String, defaultValue: Float = 0f): Float
    fun getLong(key: String, defaultValue: Long = 0L): Long
    fun <T> get(key: String, clazz: Class<T>): T?
    fun getAll(): Map<String, *>
}
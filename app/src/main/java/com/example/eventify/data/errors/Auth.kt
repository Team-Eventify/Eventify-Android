package com.example.eventify.data.errors


open class NotAuthenticated(message: String = "Пользователь не авторизован."): Exception(message)
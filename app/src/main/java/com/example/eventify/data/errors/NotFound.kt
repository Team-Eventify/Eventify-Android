package com.example.eventify.data.errors



open class NotFoundException(message: String): Exception(message)

class UserNotFoundException(message: String = "Пользователь не найден."): Exception(message)
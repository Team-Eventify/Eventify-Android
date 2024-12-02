package com.example.eventify.data.exceptions



open class NotFoundException(message: String): Exception(message)

class UserNotFoundException(message: String = "Пользователь не найден."): Exception(message)

class CategoryNotFoundException(message: String = "Категория не найдена."): Exception(message)

class EventNotFoundException(message: String = "Пост не найден"): Exception(message)
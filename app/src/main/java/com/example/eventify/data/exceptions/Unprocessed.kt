package com.example.eventify.data.exceptions


class UnprocessedServerResponseException(message: String = "Ошибка сервера"): Exception(message)

class NullableResponseException(message: String = "Ошибка сервера"): Exception(message)
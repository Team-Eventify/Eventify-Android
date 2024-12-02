package com.example.eventify.data.exceptions


class UnprocessedServerResponseException(message: String = "Ошибка сервера"): Exception(message)

class EmptyResponseException(message: String = "Ошибка сервера"): Exception(message)
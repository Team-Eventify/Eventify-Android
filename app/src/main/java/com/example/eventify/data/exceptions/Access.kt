package com.example.eventify.data.exceptions


class AccessDeniedException(message: String = "Доступ к ресурсу запрещен") : Exception(message)
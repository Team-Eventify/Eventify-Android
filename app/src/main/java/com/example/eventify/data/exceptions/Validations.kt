package com.example.eventify.data.exceptions


class ValidationError(override val message: String) : Exception(message)
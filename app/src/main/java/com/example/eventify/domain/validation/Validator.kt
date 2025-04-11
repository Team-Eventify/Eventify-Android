package com.example.eventify.domain.validation

import com.example.eventify.domain.Error
import com.example.eventify.domain.Result


interface Validator<T, out E: Error> {
    operator fun invoke(value: T): Result<T, E>
}
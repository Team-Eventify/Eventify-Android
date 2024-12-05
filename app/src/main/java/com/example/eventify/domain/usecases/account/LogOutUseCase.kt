package com.example.eventify.domain.usecases.account

import com.example.eventify.data.repositories.tokens.TokenManager
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val tokenManager: TokenManager
)  {
    operator fun invoke(){
        tokenManager.clear()
    }
}
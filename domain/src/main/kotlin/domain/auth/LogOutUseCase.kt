package domain.auth

import core.common.secure.tokens.TokenProvider
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val tokenProvider: TokenProvider
)  {
    operator fun invoke(){
        tokenProvider.clear()
    }
}
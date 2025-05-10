package core.domain.auth

import com.example.eventify.data.repositories.tokens.TokenProvider
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val tokenProvider: TokenProvider
)  {
    operator fun invoke(){
        tokenProvider.clear()
    }
}
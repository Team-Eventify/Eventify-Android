package domain.di

import core.common.secure.tokens.TokenProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.repositories.users.UsersRepository
import domain.SessionManager
import domain.SessionManagerRequestsImpl
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object SessionManagerModule {
    @Provides
    @Singleton
    fun provideDecodeSessionManager(usersRepository: UsersRepository, tokenProvider: TokenProvider): SessionManager = SessionManagerRequestsImpl(
        tokenProvider = tokenProvider,
        usersRepository = usersRepository,
    )
}
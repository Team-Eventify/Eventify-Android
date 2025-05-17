package domain.di;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.users.UsersRepository;
import domain.SessionManager;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class SessionManagerModule_ProvideDecodeSessionManagerFactory implements Factory<SessionManager> {
  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public SessionManagerModule_ProvideDecodeSessionManagerFactory(
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public SessionManager get() {
    return provideDecodeSessionManager(usersRepositoryProvider.get(), tokenProvider.get());
  }

  public static SessionManagerModule_ProvideDecodeSessionManagerFactory create(
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new SessionManagerModule_ProvideDecodeSessionManagerFactory(usersRepositoryProvider, tokenProvider);
  }

  public static SessionManager provideDecodeSessionManager(UsersRepository usersRepository,
      TokenProvider tokenProvider) {
    return Preconditions.checkNotNullFromProvides(SessionManagerModule.INSTANCE.provideDecodeSessionManager(usersRepository, tokenProvider));
  }
}

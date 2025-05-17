package domain;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.users.UsersRepository;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class SessionManagerRequestsImpl_Factory implements Factory<SessionManagerRequestsImpl> {
  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public SessionManagerRequestsImpl_Factory(Provider<UsersRepository> usersRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public SessionManagerRequestsImpl get() {
    return newInstance(usersRepositoryProvider.get(), tokenProvider.get());
  }

  public static SessionManagerRequestsImpl_Factory create(
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new SessionManagerRequestsImpl_Factory(usersRepositoryProvider, tokenProvider);
  }

  public static SessionManagerRequestsImpl newInstance(UsersRepository usersRepository,
      TokenProvider tokenProvider) {
    return new SessionManagerRequestsImpl(usersRepository, tokenProvider);
  }
}

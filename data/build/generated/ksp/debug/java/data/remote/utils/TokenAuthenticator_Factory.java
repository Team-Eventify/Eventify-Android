package data.remote.utils;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.auth.AuthUserRepository;
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
public final class TokenAuthenticator_Factory implements Factory<TokenAuthenticator> {
  private final Provider<TokenProvider> tokenProvider;

  private final Provider<AuthUserRepository> authRepositoryProvider;

  public TokenAuthenticator_Factory(Provider<TokenProvider> tokenProvider,
      Provider<AuthUserRepository> authRepositoryProvider) {
    this.tokenProvider = tokenProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public TokenAuthenticator get() {
    return newInstance(tokenProvider.get(), authRepositoryProvider.get());
  }

  public static TokenAuthenticator_Factory create(Provider<TokenProvider> tokenProvider,
      Provider<AuthUserRepository> authRepositoryProvider) {
    return new TokenAuthenticator_Factory(tokenProvider, authRepositoryProvider);
  }

  public static TokenAuthenticator newInstance(TokenProvider tokenProvider,
      AuthUserRepository authRepository) {
    return new TokenAuthenticator(tokenProvider, authRepository);
  }
}

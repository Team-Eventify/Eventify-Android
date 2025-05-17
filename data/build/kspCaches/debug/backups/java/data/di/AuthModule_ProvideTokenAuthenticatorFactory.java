package data.di;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.auth.AuthUserRepository;
import javax.annotation.processing.Generated;
import okhttp3.Authenticator;

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
public final class AuthModule_ProvideTokenAuthenticatorFactory implements Factory<Authenticator> {
  private final Provider<TokenProvider> tokenProvider;

  private final Provider<AuthUserRepository> authRepositoryProvider;

  public AuthModule_ProvideTokenAuthenticatorFactory(Provider<TokenProvider> tokenProvider,
      Provider<AuthUserRepository> authRepositoryProvider) {
    this.tokenProvider = tokenProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public Authenticator get() {
    return provideTokenAuthenticator(tokenProvider.get(), authRepositoryProvider.get());
  }

  public static AuthModule_ProvideTokenAuthenticatorFactory create(
      Provider<TokenProvider> tokenProvider, Provider<AuthUserRepository> authRepositoryProvider) {
    return new AuthModule_ProvideTokenAuthenticatorFactory(tokenProvider, authRepositoryProvider);
  }

  public static Authenticator provideTokenAuthenticator(TokenProvider tokenProvider,
      AuthUserRepository authRepository) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideTokenAuthenticator(tokenProvider, authRepository));
  }
}

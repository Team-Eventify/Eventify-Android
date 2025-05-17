package domain.auth;

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
public final class LoginUseCase_Factory implements Factory<LoginUseCase> {
  private final Provider<TokenProvider> tokenProvider;

  private final Provider<AuthUserRepository> authRepositoryProvider;

  public LoginUseCase_Factory(Provider<TokenProvider> tokenProvider,
      Provider<AuthUserRepository> authRepositoryProvider) {
    this.tokenProvider = tokenProvider;
    this.authRepositoryProvider = authRepositoryProvider;
  }

  @Override
  public LoginUseCase get() {
    return newInstance(tokenProvider.get(), authRepositoryProvider.get());
  }

  public static LoginUseCase_Factory create(Provider<TokenProvider> tokenProvider,
      Provider<AuthUserRepository> authRepositoryProvider) {
    return new LoginUseCase_Factory(tokenProvider, authRepositoryProvider);
  }

  public static LoginUseCase newInstance(TokenProvider tokenProvider,
      AuthUserRepository authRepository) {
    return new LoginUseCase(tokenProvider, authRepository);
  }
}

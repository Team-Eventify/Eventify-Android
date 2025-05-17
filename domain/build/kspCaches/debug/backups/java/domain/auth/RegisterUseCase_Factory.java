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
public final class RegisterUseCase_Factory implements Factory<RegisterUseCase> {
  private final Provider<AuthUserRepository> authRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public RegisterUseCase_Factory(Provider<AuthUserRepository> authRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.authRepositoryProvider = authRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public RegisterUseCase get() {
    return newInstance(authRepositoryProvider.get(), tokenProvider.get());
  }

  public static RegisterUseCase_Factory create(Provider<AuthUserRepository> authRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    return new RegisterUseCase_Factory(authRepositoryProvider, tokenProvider);
  }

  public static RegisterUseCase newInstance(AuthUserRepository authRepository,
      TokenProvider tokenProvider) {
    return new RegisterUseCase(authRepository, tokenProvider);
  }
}

package domain.account;

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
public final class GetCurrentUserUseCase_Factory implements Factory<GetCurrentUserUseCase> {
  private final Provider<TokenProvider> tokenProvider;

  private final Provider<UsersRepository> usersRepositoryProvider;

  public GetCurrentUserUseCase_Factory(Provider<TokenProvider> tokenProvider,
      Provider<UsersRepository> usersRepositoryProvider) {
    this.tokenProvider = tokenProvider;
    this.usersRepositoryProvider = usersRepositoryProvider;
  }

  @Override
  public GetCurrentUserUseCase get() {
    return newInstance(tokenProvider.get(), usersRepositoryProvider.get());
  }

  public static GetCurrentUserUseCase_Factory create(Provider<TokenProvider> tokenProvider,
      Provider<UsersRepository> usersRepositoryProvider) {
    return new GetCurrentUserUseCase_Factory(tokenProvider, usersRepositoryProvider);
  }

  public static GetCurrentUserUseCase newInstance(TokenProvider tokenProvider,
      UsersRepository usersRepository) {
    return new GetCurrentUserUseCase(tokenProvider, usersRepository);
  }
}

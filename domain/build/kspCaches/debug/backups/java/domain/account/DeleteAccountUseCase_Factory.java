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
public final class DeleteAccountUseCase_Factory implements Factory<DeleteAccountUseCase> {
  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public DeleteAccountUseCase_Factory(Provider<UsersRepository> usersRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public DeleteAccountUseCase get() {
    return newInstance(usersRepositoryProvider.get(), tokenProvider.get());
  }

  public static DeleteAccountUseCase_Factory create(
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new DeleteAccountUseCase_Factory(usersRepositoryProvider, tokenProvider);
  }

  public static DeleteAccountUseCase newInstance(UsersRepository usersRepository,
      TokenProvider tokenProvider) {
    return new DeleteAccountUseCase(usersRepository, tokenProvider);
  }
}

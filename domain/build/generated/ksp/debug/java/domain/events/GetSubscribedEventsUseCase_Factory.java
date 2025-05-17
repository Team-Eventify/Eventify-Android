package domain.events;

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
public final class GetSubscribedEventsUseCase_Factory implements Factory<GetSubscribedEventsUseCase> {
  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public GetSubscribedEventsUseCase_Factory(Provider<UsersRepository> usersRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public GetSubscribedEventsUseCase get() {
    return newInstance(usersRepositoryProvider.get(), tokenProvider.get());
  }

  public static GetSubscribedEventsUseCase_Factory create(
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new GetSubscribedEventsUseCase_Factory(usersRepositoryProvider, tokenProvider);
  }

  public static GetSubscribedEventsUseCase newInstance(UsersRepository usersRepository,
      TokenProvider tokenProvider) {
    return new GetSubscribedEventsUseCase(usersRepository, tokenProvider);
  }
}

package domain.events;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.events.EventsRepository;
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
public final class GetEventsUseCase_Factory implements Factory<GetEventsUseCase> {
  private final Provider<EventsRepository> eventsApiRepositoryProvider;

  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public GetEventsUseCase_Factory(Provider<EventsRepository> eventsApiRepositoryProvider,
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    this.eventsApiRepositoryProvider = eventsApiRepositoryProvider;
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public GetEventsUseCase get() {
    return newInstance(eventsApiRepositoryProvider.get(), usersRepositoryProvider.get(), tokenProvider.get());
  }

  public static GetEventsUseCase_Factory create(
      Provider<EventsRepository> eventsApiRepositoryProvider,
      Provider<UsersRepository> usersRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new GetEventsUseCase_Factory(eventsApiRepositoryProvider, usersRepositoryProvider, tokenProvider);
  }

  public static GetEventsUseCase newInstance(EventsRepository eventsApiRepository,
      UsersRepository usersRepository, TokenProvider tokenProvider) {
    return new GetEventsUseCase(eventsApiRepository, usersRepository, tokenProvider);
  }
}

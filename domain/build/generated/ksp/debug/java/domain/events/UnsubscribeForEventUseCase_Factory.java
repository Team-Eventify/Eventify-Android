package domain.events;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.events.EventsRepository;
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
public final class UnsubscribeForEventUseCase_Factory implements Factory<UnsubscribeForEventUseCase> {
  private final Provider<EventsRepository> eventsRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public UnsubscribeForEventUseCase_Factory(Provider<EventsRepository> eventsRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.eventsRepositoryProvider = eventsRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public UnsubscribeForEventUseCase get() {
    return newInstance(eventsRepositoryProvider.get(), tokenProvider.get());
  }

  public static UnsubscribeForEventUseCase_Factory create(
      Provider<EventsRepository> eventsRepositoryProvider, Provider<TokenProvider> tokenProvider) {
    return new UnsubscribeForEventUseCase_Factory(eventsRepositoryProvider, tokenProvider);
  }

  public static UnsubscribeForEventUseCase newInstance(EventsRepository eventsRepository,
      TokenProvider tokenProvider) {
    return new UnsubscribeForEventUseCase(eventsRepository, tokenProvider);
  }
}

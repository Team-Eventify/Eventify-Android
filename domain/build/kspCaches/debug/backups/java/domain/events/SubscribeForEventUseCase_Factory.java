package domain.events;

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
public final class SubscribeForEventUseCase_Factory implements Factory<SubscribeForEventUseCase> {
  private final Provider<EventsRepository> eventsRepositoryProvider;

  public SubscribeForEventUseCase_Factory(Provider<EventsRepository> eventsRepositoryProvider) {
    this.eventsRepositoryProvider = eventsRepositoryProvider;
  }

  @Override
  public SubscribeForEventUseCase get() {
    return newInstance(eventsRepositoryProvider.get());
  }

  public static SubscribeForEventUseCase_Factory create(
      Provider<EventsRepository> eventsRepositoryProvider) {
    return new SubscribeForEventUseCase_Factory(eventsRepositoryProvider);
  }

  public static SubscribeForEventUseCase newInstance(EventsRepository eventsRepository) {
    return new SubscribeForEventUseCase(eventsRepository);
  }
}

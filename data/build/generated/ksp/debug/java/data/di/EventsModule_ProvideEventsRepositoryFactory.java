package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.EventsAPI;
import data.repositories.events.EventsRepository;
import javax.annotation.processing.Generated;

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
public final class EventsModule_ProvideEventsRepositoryFactory implements Factory<EventsRepository> {
  private final Provider<EventsAPI> eventsAPIProvider;

  public EventsModule_ProvideEventsRepositoryFactory(Provider<EventsAPI> eventsAPIProvider) {
    this.eventsAPIProvider = eventsAPIProvider;
  }

  @Override
  public EventsRepository get() {
    return provideEventsRepository(eventsAPIProvider.get());
  }

  public static EventsModule_ProvideEventsRepositoryFactory create(
      Provider<EventsAPI> eventsAPIProvider) {
    return new EventsModule_ProvideEventsRepositoryFactory(eventsAPIProvider);
  }

  public static EventsRepository provideEventsRepository(EventsAPI eventsAPI) {
    return Preconditions.checkNotNullFromProvides(EventsModule.INSTANCE.provideEventsRepository(eventsAPI));
  }
}

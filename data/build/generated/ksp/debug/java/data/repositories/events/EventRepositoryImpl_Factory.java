package data.repositories.events;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.EventsAPI;
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
public final class EventRepositoryImpl_Factory implements Factory<EventRepositoryImpl> {
  private final Provider<EventsAPI> dataSourceProvider;

  public EventRepositoryImpl_Factory(Provider<EventsAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public EventRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static EventRepositoryImpl_Factory create(Provider<EventsAPI> dataSourceProvider) {
    return new EventRepositoryImpl_Factory(dataSourceProvider);
  }

  public static EventRepositoryImpl newInstance(EventsAPI dataSource) {
    return new EventRepositoryImpl(dataSource);
  }
}

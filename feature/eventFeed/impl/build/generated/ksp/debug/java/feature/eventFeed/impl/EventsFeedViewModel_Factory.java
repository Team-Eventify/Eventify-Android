package feature.eventFeed.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.events.GetEventsUseCase;
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
public final class EventsFeedViewModel_Factory implements Factory<EventsFeedViewModel> {
  private final Provider<GetEventsUseCase> getEventsUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public EventsFeedViewModel_Factory(Provider<GetEventsUseCase> getEventsUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.getEventsUseCaseProvider = getEventsUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public EventsFeedViewModel get() {
    EventsFeedViewModel instance = newInstance(getEventsUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static EventsFeedViewModel_Factory create(
      Provider<GetEventsUseCase> getEventsUseCaseProvider, Provider<AuthProvider> authProvider) {
    return new EventsFeedViewModel_Factory(getEventsUseCaseProvider, authProvider);
  }

  public static EventsFeedViewModel newInstance(GetEventsUseCase getEventsUseCase) {
    return new EventsFeedViewModel(getEventsUseCase);
  }
}

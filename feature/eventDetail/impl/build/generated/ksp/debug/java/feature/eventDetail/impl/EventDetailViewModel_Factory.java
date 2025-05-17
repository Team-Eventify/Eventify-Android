package feature.eventDetail.impl;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.events.GetEventDetailUseCase;
import domain.events.SubscribeForEventUseCase;
import domain.events.UnsubscribeForEventUseCase;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class EventDetailViewModel_Factory implements Factory<EventDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetEventDetailUseCase> getEventDetailUseCaseProvider;

  private final Provider<SubscribeForEventUseCase> subscribedEventsUseCaseProvider;

  private final Provider<UnsubscribeForEventUseCase> unsubscribeForEventUseCaseProvider;

  private final Provider<Context> contextProvider;

  private final Provider<AuthProvider> authProvider;

  public EventDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetEventDetailUseCase> getEventDetailUseCaseProvider,
      Provider<SubscribeForEventUseCase> subscribedEventsUseCaseProvider,
      Provider<UnsubscribeForEventUseCase> unsubscribeForEventUseCaseProvider,
      Provider<Context> contextProvider, Provider<AuthProvider> authProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getEventDetailUseCaseProvider = getEventDetailUseCaseProvider;
    this.subscribedEventsUseCaseProvider = subscribedEventsUseCaseProvider;
    this.unsubscribeForEventUseCaseProvider = unsubscribeForEventUseCaseProvider;
    this.contextProvider = contextProvider;
    this.authProvider = authProvider;
  }

  @Override
  public EventDetailViewModel get() {
    EventDetailViewModel instance = newInstance(savedStateHandleProvider.get(), getEventDetailUseCaseProvider.get(), subscribedEventsUseCaseProvider.get(), unsubscribeForEventUseCaseProvider.get(), contextProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static EventDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetEventDetailUseCase> getEventDetailUseCaseProvider,
      Provider<SubscribeForEventUseCase> subscribedEventsUseCaseProvider,
      Provider<UnsubscribeForEventUseCase> unsubscribeForEventUseCaseProvider,
      Provider<Context> contextProvider, Provider<AuthProvider> authProvider) {
    return new EventDetailViewModel_Factory(savedStateHandleProvider, getEventDetailUseCaseProvider, subscribedEventsUseCaseProvider, unsubscribeForEventUseCaseProvider, contextProvider, authProvider);
  }

  public static EventDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      GetEventDetailUseCase getEventDetailUseCase, SubscribeForEventUseCase subscribedEventsUseCase,
      UnsubscribeForEventUseCase unsubscribeForEventUseCase, Context context) {
    return new EventDetailViewModel(savedStateHandle, getEventDetailUseCase, subscribedEventsUseCase, unsubscribeForEventUseCase, context);
  }
}

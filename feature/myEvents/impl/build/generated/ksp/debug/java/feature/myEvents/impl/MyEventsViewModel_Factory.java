package feature.myEvents.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.events.GetSubscribedEventsUseCase;
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
public final class MyEventsViewModel_Factory implements Factory<MyEventsViewModel> {
  private final Provider<GetSubscribedEventsUseCase> getSubscribedEventsUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public MyEventsViewModel_Factory(
      Provider<GetSubscribedEventsUseCase> getSubscribedEventsUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.getSubscribedEventsUseCaseProvider = getSubscribedEventsUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public MyEventsViewModel get() {
    MyEventsViewModel instance = newInstance(getSubscribedEventsUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static MyEventsViewModel_Factory create(
      Provider<GetSubscribedEventsUseCase> getSubscribedEventsUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    return new MyEventsViewModel_Factory(getSubscribedEventsUseCaseProvider, authProvider);
  }

  public static MyEventsViewModel newInstance(
      GetSubscribedEventsUseCase getSubscribedEventsUseCase) {
    return new MyEventsViewModel(getSubscribedEventsUseCase);
  }
}

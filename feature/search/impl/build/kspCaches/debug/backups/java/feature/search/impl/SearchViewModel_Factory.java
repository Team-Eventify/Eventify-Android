package feature.search.impl;

import androidx.lifecycle.SavedStateHandle;
import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.categories.GetCategoriesUseCase;
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
public final class SearchViewModel_Factory implements Factory<SearchViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<GetEventsUseCase> getEventsUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public SearchViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider, Provider<AuthProvider> authProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.getEventsUseCaseProvider = getEventsUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public SearchViewModel get() {
    SearchViewModel instance = newInstance(savedStateHandleProvider.get(), getCategoriesUseCaseProvider.get(), getEventsUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static SearchViewModel_Factory create(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider, Provider<AuthProvider> authProvider) {
    return new SearchViewModel_Factory(savedStateHandleProvider, getCategoriesUseCaseProvider, getEventsUseCaseProvider, authProvider);
  }

  public static SearchViewModel newInstance(SavedStateHandle savedStateHandle,
      GetCategoriesUseCase getCategoriesUseCase, GetEventsUseCase getEventsUseCase) {
    return new SearchViewModel(savedStateHandle, getCategoriesUseCase, getEventsUseCase);
  }
}

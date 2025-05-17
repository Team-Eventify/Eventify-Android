package feature.searchResult.impl.ui;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.category.CategoryRepository;
import domain.events.GetEventsUseCase;
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
public final class SearchDetailViewModel_Factory implements Factory<SearchDetailViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<GetEventsUseCase> getEventsUseCaseProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<Context> contextProvider;

  private final Provider<AuthProvider> authProvider;

  public SearchDetailViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider, Provider<Context> contextProvider,
      Provider<AuthProvider> authProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.getEventsUseCaseProvider = getEventsUseCaseProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.contextProvider = contextProvider;
    this.authProvider = authProvider;
  }

  @Override
  public SearchDetailViewModel get() {
    SearchDetailViewModel instance = newInstance(savedStateHandleProvider.get(), getEventsUseCaseProvider.get(), categoryRepositoryProvider.get(), contextProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static SearchDetailViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<GetEventsUseCase> getEventsUseCaseProvider,
      Provider<CategoryRepository> categoryRepositoryProvider, Provider<Context> contextProvider,
      Provider<AuthProvider> authProvider) {
    return new SearchDetailViewModel_Factory(savedStateHandleProvider, getEventsUseCaseProvider, categoryRepositoryProvider, contextProvider, authProvider);
  }

  public static SearchDetailViewModel newInstance(SavedStateHandle savedStateHandle,
      GetEventsUseCase getEventsUseCase, CategoryRepository categoryRepository, Context context) {
    return new SearchDetailViewModel(savedStateHandle, getEventsUseCase, categoryRepository, context);
  }
}

package feature.setup.impl.ui;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.account.ChangeUserUseCase;
import domain.account.SetUserCategoriesUseCase;
import domain.categories.GetCategoriesUseCase;
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
public final class ChooseCategoriesViewModel_Factory implements Factory<ChooseCategoriesViewModel> {
  private final Provider<SetUserCategoriesUseCase> setCategoriesUseCaseProvider;

  private final Provider<ChangeUserUseCase> changeUserProvider;

  private final Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public ChooseCategoriesViewModel_Factory(
      Provider<SetUserCategoriesUseCase> setCategoriesUseCaseProvider,
      Provider<ChangeUserUseCase> changeUserProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.setCategoriesUseCaseProvider = setCategoriesUseCaseProvider;
    this.changeUserProvider = changeUserProvider;
    this.getCategoriesUseCaseProvider = getCategoriesUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ChooseCategoriesViewModel get() {
    ChooseCategoriesViewModel instance = newInstance(setCategoriesUseCaseProvider.get(), changeUserProvider.get(), getCategoriesUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static ChooseCategoriesViewModel_Factory create(
      Provider<SetUserCategoriesUseCase> setCategoriesUseCaseProvider,
      Provider<ChangeUserUseCase> changeUserProvider,
      Provider<GetCategoriesUseCase> getCategoriesUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    return new ChooseCategoriesViewModel_Factory(setCategoriesUseCaseProvider, changeUserProvider, getCategoriesUseCaseProvider, authProvider);
  }

  public static ChooseCategoriesViewModel newInstance(SetUserCategoriesUseCase setCategoriesUseCase,
      ChangeUserUseCase changeUser, GetCategoriesUseCase getCategoriesUseCase) {
    return new ChooseCategoriesViewModel(setCategoriesUseCase, changeUser, getCategoriesUseCase);
  }
}

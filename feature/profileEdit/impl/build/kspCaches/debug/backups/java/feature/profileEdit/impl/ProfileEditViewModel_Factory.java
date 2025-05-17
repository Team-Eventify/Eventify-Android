package feature.profileEdit.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.GetCategoriesWithUserSelection;
import domain.account.ChangeUserUseCase;
import domain.account.DeleteAccountUseCase;
import domain.account.GetCurrentUserUseCase;
import domain.account.SetUserCategoriesUseCase;
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
public final class ProfileEditViewModel_Factory implements Factory<ProfileEditViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  private final Provider<GetCategoriesWithUserSelection> getCategoriesWithUserSelectionProvider;

  private final Provider<ChangeUserUseCase> changeUserUseCaseProvider;

  private final Provider<SetUserCategoriesUseCase> setUserCategoriesUseCaseProvider;

  private final Provider<DeleteAccountUseCase> deleteAccountUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public ProfileEditViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<GetCategoriesWithUserSelection> getCategoriesWithUserSelectionProvider,
      Provider<ChangeUserUseCase> changeUserUseCaseProvider,
      Provider<SetUserCategoriesUseCase> setUserCategoriesUseCaseProvider,
      Provider<DeleteAccountUseCase> deleteAccountUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
    this.getCategoriesWithUserSelectionProvider = getCategoriesWithUserSelectionProvider;
    this.changeUserUseCaseProvider = changeUserUseCaseProvider;
    this.setUserCategoriesUseCaseProvider = setUserCategoriesUseCaseProvider;
    this.deleteAccountUseCaseProvider = deleteAccountUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileEditViewModel get() {
    ProfileEditViewModel instance = newInstance(getCurrentUserUseCaseProvider.get(), getCategoriesWithUserSelectionProvider.get(), changeUserUseCaseProvider.get(), setUserCategoriesUseCaseProvider.get(), deleteAccountUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static ProfileEditViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<GetCategoriesWithUserSelection> getCategoriesWithUserSelectionProvider,
      Provider<ChangeUserUseCase> changeUserUseCaseProvider,
      Provider<SetUserCategoriesUseCase> setUserCategoriesUseCaseProvider,
      Provider<DeleteAccountUseCase> deleteAccountUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    return new ProfileEditViewModel_Factory(getCurrentUserUseCaseProvider, getCategoriesWithUserSelectionProvider, changeUserUseCaseProvider, setUserCategoriesUseCaseProvider, deleteAccountUseCaseProvider, authProvider);
  }

  public static ProfileEditViewModel newInstance(GetCurrentUserUseCase getCurrentUserUseCase,
      GetCategoriesWithUserSelection getCategoriesWithUserSelection,
      ChangeUserUseCase changeUserUseCase, SetUserCategoriesUseCase setUserCategoriesUseCase,
      DeleteAccountUseCase deleteAccountUseCase) {
    return new ProfileEditViewModel(getCurrentUserUseCase, getCategoriesWithUserSelection, changeUserUseCase, setUserCategoriesUseCase, deleteAccountUseCase);
  }
}

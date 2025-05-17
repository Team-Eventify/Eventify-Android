package feature.profile.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.account.GetCurrentUserUseCase;
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
public final class ProfileViewModel_Factory implements Factory<ProfileViewModel> {
  private final Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public ProfileViewModel_Factory(Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.getCurrentUserUseCaseProvider = getCurrentUserUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public ProfileViewModel get() {
    ProfileViewModel instance = newInstance(getCurrentUserUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static ProfileViewModel_Factory create(
      Provider<GetCurrentUserUseCase> getCurrentUserUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    return new ProfileViewModel_Factory(getCurrentUserUseCaseProvider, authProvider);
  }

  public static ProfileViewModel newInstance(GetCurrentUserUseCase getCurrentUserUseCase) {
    return new ProfileViewModel(getCurrentUserUseCase);
  }
}

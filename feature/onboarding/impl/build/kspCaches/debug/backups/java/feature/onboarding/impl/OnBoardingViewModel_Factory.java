package feature.onboarding.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import core.common.storages.LocaleStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("core.common.storages.SharedStorage")
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
public final class OnBoardingViewModel_Factory implements Factory<OnBoardingViewModel> {
  private final Provider<LocaleStorage> localeStorageProvider;

  private final Provider<AuthProvider> authProvider;

  public OnBoardingViewModel_Factory(Provider<LocaleStorage> localeStorageProvider,
      Provider<AuthProvider> authProvider) {
    this.localeStorageProvider = localeStorageProvider;
    this.authProvider = authProvider;
  }

  @Override
  public OnBoardingViewModel get() {
    OnBoardingViewModel instance = newInstance(localeStorageProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static OnBoardingViewModel_Factory create(Provider<LocaleStorage> localeStorageProvider,
      Provider<AuthProvider> authProvider) {
    return new OnBoardingViewModel_Factory(localeStorageProvider, authProvider);
  }

  public static OnBoardingViewModel newInstance(LocaleStorage localeStorage) {
    return new OnBoardingViewModel(localeStorage);
  }
}

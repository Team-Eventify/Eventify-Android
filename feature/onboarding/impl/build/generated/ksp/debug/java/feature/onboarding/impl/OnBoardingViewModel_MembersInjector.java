package feature.onboarding.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;

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
public final class OnBoardingViewModel_MembersInjector implements MembersInjector<OnBoardingViewModel> {
  private final Provider<AuthProvider> authProvider;

  public OnBoardingViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<OnBoardingViewModel> create(Provider<AuthProvider> authProvider) {
    return new OnBoardingViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(OnBoardingViewModel instance) {
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
  }
}

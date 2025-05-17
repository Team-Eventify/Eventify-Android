package feature.profileEdit.impl;

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
public final class ProfileEditViewModel_MembersInjector implements MembersInjector<ProfileEditViewModel> {
  private final Provider<AuthProvider> authProvider;

  public ProfileEditViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<ProfileEditViewModel> create(Provider<AuthProvider> authProvider) {
    return new ProfileEditViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(ProfileEditViewModel instance) {
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
  }
}

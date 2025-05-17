package feature.register.impl;

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
public final class RegisterViewModel_MembersInjector implements MembersInjector<RegisterViewModel> {
  private final Provider<AuthProvider> authProvider;

  public RegisterViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<RegisterViewModel> create(Provider<AuthProvider> authProvider) {
    return new RegisterViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(RegisterViewModel instance) {
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
  }
}

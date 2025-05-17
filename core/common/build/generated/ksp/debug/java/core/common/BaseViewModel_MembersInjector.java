package core.common;

import core.common.secure.AuthProvider;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
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
public final class BaseViewModel_MembersInjector implements MembersInjector<BaseViewModel> {
  private final Provider<AuthProvider> authProvider;

  public BaseViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<BaseViewModel> create(Provider<AuthProvider> authProvider) {
    return new BaseViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(BaseViewModel instance) {
    injectAuthProvider(instance, authProvider.get());
  }

  @InjectedFieldSignature("core.common.BaseViewModel.authProvider")
  public static void injectAuthProvider(BaseViewModel instance, AuthProvider authProvider) {
    instance.authProvider = authProvider;
  }
}

package feature.setup.impl.ui;

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
public final class ChooseCategoriesViewModel_MembersInjector implements MembersInjector<ChooseCategoriesViewModel> {
  private final Provider<AuthProvider> authProvider;

  public ChooseCategoriesViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<ChooseCategoriesViewModel> create(
      Provider<AuthProvider> authProvider) {
    return new ChooseCategoriesViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(ChooseCategoriesViewModel instance) {
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
  }
}

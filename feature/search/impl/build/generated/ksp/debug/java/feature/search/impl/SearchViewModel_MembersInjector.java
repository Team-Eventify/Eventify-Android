package feature.search.impl;

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
public final class SearchViewModel_MembersInjector implements MembersInjector<SearchViewModel> {
  private final Provider<AuthProvider> authProvider;

  public SearchViewModel_MembersInjector(Provider<AuthProvider> authProvider) {
    this.authProvider = authProvider;
  }

  public static MembersInjector<SearchViewModel> create(Provider<AuthProvider> authProvider) {
    return new SearchViewModel_MembersInjector(authProvider);
  }

  @Override
  public void injectMembers(SearchViewModel instance) {
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
  }
}

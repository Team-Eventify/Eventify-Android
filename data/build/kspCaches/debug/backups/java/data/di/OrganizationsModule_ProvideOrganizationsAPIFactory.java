package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.OrganizationsAPI;
import data.remote.utils.AccessTokenInterceptor;
import data.remote.utils.TokenAuthenticator;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class OrganizationsModule_ProvideOrganizationsAPIFactory implements Factory<OrganizationsAPI> {
  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  public OrganizationsModule_ProvideOrganizationsAPIFactory(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
  }

  @Override
  public OrganizationsAPI get() {
    return provideOrganizationsAPI(accessTokenInterceptorProvider.get(), tokenAuthenticatorProvider.get());
  }

  public static OrganizationsModule_ProvideOrganizationsAPIFactory create(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    return new OrganizationsModule_ProvideOrganizationsAPIFactory(accessTokenInterceptorProvider, tokenAuthenticatorProvider);
  }

  public static OrganizationsAPI provideOrganizationsAPI(
      AccessTokenInterceptor accessTokenInterceptor, TokenAuthenticator tokenAuthenticator) {
    return Preconditions.checkNotNullFromProvides(OrganizationsModule.INSTANCE.provideOrganizationsAPI(accessTokenInterceptor, tokenAuthenticator));
  }
}

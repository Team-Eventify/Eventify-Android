package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.CategoryAPI;
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
public final class CategoryModule_ProvideCategoriesAPIFactory implements Factory<CategoryAPI> {
  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  public CategoryModule_ProvideCategoriesAPIFactory(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
  }

  @Override
  public CategoryAPI get() {
    return provideCategoriesAPI(accessTokenInterceptorProvider.get(), tokenAuthenticatorProvider.get());
  }

  public static CategoryModule_ProvideCategoriesAPIFactory create(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    return new CategoryModule_ProvideCategoriesAPIFactory(accessTokenInterceptorProvider, tokenAuthenticatorProvider);
  }

  public static CategoryAPI provideCategoriesAPI(AccessTokenInterceptor accessTokenInterceptor,
      TokenAuthenticator tokenAuthenticator) {
    return Preconditions.checkNotNullFromProvides(CategoryModule.INSTANCE.provideCategoriesAPI(accessTokenInterceptor, tokenAuthenticator));
  }
}

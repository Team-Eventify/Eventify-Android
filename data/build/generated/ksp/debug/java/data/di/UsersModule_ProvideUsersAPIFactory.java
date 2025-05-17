package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.UsersAPI;
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
public final class UsersModule_ProvideUsersAPIFactory implements Factory<UsersAPI> {
  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  public UsersModule_ProvideUsersAPIFactory(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
  }

  @Override
  public UsersAPI get() {
    return provideUsersAPI(accessTokenInterceptorProvider.get(), tokenAuthenticatorProvider.get());
  }

  public static UsersModule_ProvideUsersAPIFactory create(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    return new UsersModule_ProvideUsersAPIFactory(accessTokenInterceptorProvider, tokenAuthenticatorProvider);
  }

  public static UsersAPI provideUsersAPI(AccessTokenInterceptor accessTokenInterceptor,
      TokenAuthenticator tokenAuthenticator) {
    return Preconditions.checkNotNullFromProvides(UsersModule.INSTANCE.provideUsersAPI(accessTokenInterceptor, tokenAuthenticator));
  }
}

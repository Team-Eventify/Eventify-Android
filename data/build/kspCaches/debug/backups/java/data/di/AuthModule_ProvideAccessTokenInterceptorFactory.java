package data.di;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import okhttp3.Interceptor;

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
public final class AuthModule_ProvideAccessTokenInterceptorFactory implements Factory<Interceptor> {
  private final Provider<TokenProvider> tokenProvider;

  public AuthModule_ProvideAccessTokenInterceptorFactory(Provider<TokenProvider> tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public Interceptor get() {
    return provideAccessTokenInterceptor(tokenProvider.get());
  }

  public static AuthModule_ProvideAccessTokenInterceptorFactory create(
      Provider<TokenProvider> tokenProvider) {
    return new AuthModule_ProvideAccessTokenInterceptorFactory(tokenProvider);
  }

  public static Interceptor provideAccessTokenInterceptor(TokenProvider tokenProvider) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAccessTokenInterceptor(tokenProvider));
  }
}

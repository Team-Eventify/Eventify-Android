package data.remote.utils;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class AccessTokenInterceptor_Factory implements Factory<AccessTokenInterceptor> {
  private final Provider<TokenProvider> tokenProvider;

  public AccessTokenInterceptor_Factory(Provider<TokenProvider> tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public AccessTokenInterceptor get() {
    return newInstance(tokenProvider.get());
  }

  public static AccessTokenInterceptor_Factory create(Provider<TokenProvider> tokenProvider) {
    return new AccessTokenInterceptor_Factory(tokenProvider);
  }

  public static AccessTokenInterceptor newInstance(TokenProvider tokenProvider) {
    return new AccessTokenInterceptor(tokenProvider);
  }
}

package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.FilesAPI;
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
public final class FilesModule_ProvideFilesApiFactory implements Factory<FilesAPI> {
  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  public FilesModule_ProvideFilesApiFactory(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
  }

  @Override
  public FilesAPI get() {
    return provideFilesApi(accessTokenInterceptorProvider.get(), tokenAuthenticatorProvider.get());
  }

  public static FilesModule_ProvideFilesApiFactory create(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    return new FilesModule_ProvideFilesApiFactory(accessTokenInterceptorProvider, tokenAuthenticatorProvider);
  }

  public static FilesAPI provideFilesApi(AccessTokenInterceptor accessTokenInterceptor,
      TokenAuthenticator tokenAuthenticator) {
    return Preconditions.checkNotNullFromProvides(FilesModule.INSTANCE.provideFilesApi(accessTokenInterceptor, tokenAuthenticator));
  }
}

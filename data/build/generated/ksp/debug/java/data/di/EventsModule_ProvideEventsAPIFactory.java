package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.EventsAPI;
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
public final class EventsModule_ProvideEventsAPIFactory implements Factory<EventsAPI> {
  private final Provider<AccessTokenInterceptor> accessTokenInterceptorProvider;

  private final Provider<TokenAuthenticator> tokenAuthenticatorProvider;

  public EventsModule_ProvideEventsAPIFactory(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    this.accessTokenInterceptorProvider = accessTokenInterceptorProvider;
    this.tokenAuthenticatorProvider = tokenAuthenticatorProvider;
  }

  @Override
  public EventsAPI get() {
    return provideEventsAPI(accessTokenInterceptorProvider.get(), tokenAuthenticatorProvider.get());
  }

  public static EventsModule_ProvideEventsAPIFactory create(
      Provider<AccessTokenInterceptor> accessTokenInterceptorProvider,
      Provider<TokenAuthenticator> tokenAuthenticatorProvider) {
    return new EventsModule_ProvideEventsAPIFactory(accessTokenInterceptorProvider, tokenAuthenticatorProvider);
  }

  public static EventsAPI provideEventsAPI(AccessTokenInterceptor accessTokenInterceptor,
      TokenAuthenticator tokenAuthenticator) {
    return Preconditions.checkNotNullFromProvides(EventsModule.INSTANCE.provideEventsAPI(accessTokenInterceptor, tokenAuthenticator));
  }
}

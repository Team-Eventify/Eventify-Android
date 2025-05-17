package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.AuthAPI;
import data.repositories.auth.AuthUserRepository;
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
public final class AuthModule_ProvideAuthRepositoryFactory implements Factory<AuthUserRepository> {
  private final Provider<AuthAPI> authApiProvider;

  public AuthModule_ProvideAuthRepositoryFactory(Provider<AuthAPI> authApiProvider) {
    this.authApiProvider = authApiProvider;
  }

  @Override
  public AuthUserRepository get() {
    return provideAuthRepository(authApiProvider.get());
  }

  public static AuthModule_ProvideAuthRepositoryFactory create(Provider<AuthAPI> authApiProvider) {
    return new AuthModule_ProvideAuthRepositoryFactory(authApiProvider);
  }

  public static AuthUserRepository provideAuthRepository(AuthAPI authApi) {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthRepository(authApi));
  }
}

package data.repositories.auth;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.AuthAPI;
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
public final class AuthUserRepositoryImpl_Factory implements Factory<AuthUserRepositoryImpl> {
  private final Provider<AuthAPI> dataSourceProvider;

  public AuthUserRepositoryImpl_Factory(Provider<AuthAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public AuthUserRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static AuthUserRepositoryImpl_Factory create(Provider<AuthAPI> dataSourceProvider) {
    return new AuthUserRepositoryImpl_Factory(dataSourceProvider);
  }

  public static AuthUserRepositoryImpl newInstance(AuthAPI dataSource) {
    return new AuthUserRepositoryImpl(dataSource);
  }
}

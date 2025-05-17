package data.repositories.users;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.UsersAPI;
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
public final class UsersRepositoryImpl_Factory implements Factory<UsersRepositoryImpl> {
  private final Provider<UsersAPI> dataSourceProvider;

  public UsersRepositoryImpl_Factory(Provider<UsersAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public UsersRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static UsersRepositoryImpl_Factory create(Provider<UsersAPI> dataSourceProvider) {
    return new UsersRepositoryImpl_Factory(dataSourceProvider);
  }

  public static UsersRepositoryImpl newInstance(UsersAPI dataSource) {
    return new UsersRepositoryImpl(dataSource);
  }
}

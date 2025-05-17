package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.UsersAPI;
import data.repositories.users.UsersRepository;
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
public final class UsersModule_ProvideUsersRepositoryFactory implements Factory<UsersRepository> {
  private final Provider<UsersAPI> usersApiProvider;

  public UsersModule_ProvideUsersRepositoryFactory(Provider<UsersAPI> usersApiProvider) {
    this.usersApiProvider = usersApiProvider;
  }

  @Override
  public UsersRepository get() {
    return provideUsersRepository(usersApiProvider.get());
  }

  public static UsersModule_ProvideUsersRepositoryFactory create(
      Provider<UsersAPI> usersApiProvider) {
    return new UsersModule_ProvideUsersRepositoryFactory(usersApiProvider);
  }

  public static UsersRepository provideUsersRepository(UsersAPI usersApi) {
    return Preconditions.checkNotNullFromProvides(UsersModule.INSTANCE.provideUsersRepository(usersApi));
  }
}

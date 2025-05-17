package domain;

import core.common.secure.tokens.TokenProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.category.CategoryRepository;
import data.repositories.users.UsersRepository;
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
public final class GetCategoriesWithUserSelection_Factory implements Factory<GetCategoriesWithUserSelection> {
  private final Provider<UsersRepository> usersRepositoryProvider;

  private final Provider<CategoryRepository> categoriesRepositoryProvider;

  private final Provider<TokenProvider> tokenProvider;

  public GetCategoriesWithUserSelection_Factory(Provider<UsersRepository> usersRepositoryProvider,
      Provider<CategoryRepository> categoriesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    this.usersRepositoryProvider = usersRepositoryProvider;
    this.categoriesRepositoryProvider = categoriesRepositoryProvider;
    this.tokenProvider = tokenProvider;
  }

  @Override
  public GetCategoriesWithUserSelection get() {
    return newInstance(usersRepositoryProvider.get(), categoriesRepositoryProvider.get(), tokenProvider.get());
  }

  public static GetCategoriesWithUserSelection_Factory create(
      Provider<UsersRepository> usersRepositoryProvider,
      Provider<CategoryRepository> categoriesRepositoryProvider,
      Provider<TokenProvider> tokenProvider) {
    return new GetCategoriesWithUserSelection_Factory(usersRepositoryProvider, categoriesRepositoryProvider, tokenProvider);
  }

  public static GetCategoriesWithUserSelection newInstance(UsersRepository usersRepository,
      CategoryRepository categoriesRepository, TokenProvider tokenProvider) {
    return new GetCategoriesWithUserSelection(usersRepository, categoriesRepository, tokenProvider);
  }
}

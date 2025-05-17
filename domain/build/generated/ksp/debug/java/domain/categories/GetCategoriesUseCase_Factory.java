package domain.categories;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.category.CategoryRepository;
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
public final class GetCategoriesUseCase_Factory implements Factory<GetCategoriesUseCase> {
  private final Provider<CategoryRepository> categoriesApiRepositoryProvider;

  public GetCategoriesUseCase_Factory(
      Provider<CategoryRepository> categoriesApiRepositoryProvider) {
    this.categoriesApiRepositoryProvider = categoriesApiRepositoryProvider;
  }

  @Override
  public GetCategoriesUseCase get() {
    return newInstance(categoriesApiRepositoryProvider.get());
  }

  public static GetCategoriesUseCase_Factory create(
      Provider<CategoryRepository> categoriesApiRepositoryProvider) {
    return new GetCategoriesUseCase_Factory(categoriesApiRepositoryProvider);
  }

  public static GetCategoriesUseCase newInstance(CategoryRepository categoriesApiRepository) {
    return new GetCategoriesUseCase(categoriesApiRepository);
  }
}

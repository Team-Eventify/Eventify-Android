package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.CategoryAPI;
import data.repositories.category.CategoryRepository;
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
public final class CategoryModule_ProvideCategoriesRepositoryFactory implements Factory<CategoryRepository> {
  private final Provider<CategoryAPI> categoryApiProvider;

  public CategoryModule_ProvideCategoriesRepositoryFactory(
      Provider<CategoryAPI> categoryApiProvider) {
    this.categoryApiProvider = categoryApiProvider;
  }

  @Override
  public CategoryRepository get() {
    return provideCategoriesRepository(categoryApiProvider.get());
  }

  public static CategoryModule_ProvideCategoriesRepositoryFactory create(
      Provider<CategoryAPI> categoryApiProvider) {
    return new CategoryModule_ProvideCategoriesRepositoryFactory(categoryApiProvider);
  }

  public static CategoryRepository provideCategoriesRepository(CategoryAPI categoryApi) {
    return Preconditions.checkNotNullFromProvides(CategoryModule.INSTANCE.provideCategoriesRepository(categoryApi));
  }
}

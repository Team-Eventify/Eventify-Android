package data.repositories.category;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.CategoryAPI;
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
public final class CategoryRepositoryImpl_Factory implements Factory<CategoryRepositoryImpl> {
  private final Provider<CategoryAPI> dataSourceProvider;

  public CategoryRepositoryImpl_Factory(Provider<CategoryAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public CategoryRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static CategoryRepositoryImpl_Factory create(Provider<CategoryAPI> dataSourceProvider) {
    return new CategoryRepositoryImpl_Factory(dataSourceProvider);
  }

  public static CategoryRepositoryImpl newInstance(CategoryAPI dataSource) {
    return new CategoryRepositoryImpl(dataSource);
  }
}

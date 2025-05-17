package data.repositories.files;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.FilesAPI;
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
public final class FilesRepositoryImpl_Factory implements Factory<FilesRepositoryImpl> {
  private final Provider<FilesAPI> dataSourceProvider;

  public FilesRepositoryImpl_Factory(Provider<FilesAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public FilesRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static FilesRepositoryImpl_Factory create(Provider<FilesAPI> dataSourceProvider) {
    return new FilesRepositoryImpl_Factory(dataSourceProvider);
  }

  public static FilesRepositoryImpl newInstance(FilesAPI dataSource) {
    return new FilesRepositoryImpl(dataSource);
  }
}

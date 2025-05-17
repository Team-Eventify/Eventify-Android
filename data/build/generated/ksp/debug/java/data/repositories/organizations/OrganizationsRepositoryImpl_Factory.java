package data.repositories.organizations;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.OrganizationsAPI;
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
public final class OrganizationsRepositoryImpl_Factory implements Factory<OrganizationsRepositoryImpl> {
  private final Provider<OrganizationsAPI> dataSourceProvider;

  public OrganizationsRepositoryImpl_Factory(Provider<OrganizationsAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public OrganizationsRepositoryImpl get() {
    return newInstance(dataSourceProvider.get());
  }

  public static OrganizationsRepositoryImpl_Factory create(
      Provider<OrganizationsAPI> dataSourceProvider) {
    return new OrganizationsRepositoryImpl_Factory(dataSourceProvider);
  }

  public static OrganizationsRepositoryImpl newInstance(OrganizationsAPI dataSource) {
    return new OrganizationsRepositoryImpl(dataSource);
  }
}

package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.OrganizationsAPI;
import data.repositories.organizations.OrganizationsRepository;
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
public final class OrganizationsModule_ProvideOrganizationRepositoryFactory implements Factory<OrganizationsRepository> {
  private final Provider<OrganizationsAPI> dataSourceProvider;

  public OrganizationsModule_ProvideOrganizationRepositoryFactory(
      Provider<OrganizationsAPI> dataSourceProvider) {
    this.dataSourceProvider = dataSourceProvider;
  }

  @Override
  public OrganizationsRepository get() {
    return provideOrganizationRepository(dataSourceProvider.get());
  }

  public static OrganizationsModule_ProvideOrganizationRepositoryFactory create(
      Provider<OrganizationsAPI> dataSourceProvider) {
    return new OrganizationsModule_ProvideOrganizationRepositoryFactory(dataSourceProvider);
  }

  public static OrganizationsRepository provideOrganizationRepository(OrganizationsAPI dataSource) {
    return Preconditions.checkNotNullFromProvides(OrganizationsModule.INSTANCE.provideOrganizationRepository(dataSource));
  }
}

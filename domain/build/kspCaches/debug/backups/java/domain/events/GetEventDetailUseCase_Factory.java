package domain.events;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.category.CategoryRepository;
import data.repositories.events.EventsRepository;
import data.repositories.organizations.OrganizationsRepository;
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
public final class GetEventDetailUseCase_Factory implements Factory<GetEventDetailUseCase> {
  private final Provider<EventsRepository> eventsApiRepositoryProvider;

  private final Provider<CategoryRepository> categoryRepositoryProvider;

  private final Provider<OrganizationsRepository> organizationsRepositoryProvider;

  public GetEventDetailUseCase_Factory(Provider<EventsRepository> eventsApiRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<OrganizationsRepository> organizationsRepositoryProvider) {
    this.eventsApiRepositoryProvider = eventsApiRepositoryProvider;
    this.categoryRepositoryProvider = categoryRepositoryProvider;
    this.organizationsRepositoryProvider = organizationsRepositoryProvider;
  }

  @Override
  public GetEventDetailUseCase get() {
    return newInstance(eventsApiRepositoryProvider.get(), categoryRepositoryProvider.get(), organizationsRepositoryProvider.get());
  }

  public static GetEventDetailUseCase_Factory create(
      Provider<EventsRepository> eventsApiRepositoryProvider,
      Provider<CategoryRepository> categoryRepositoryProvider,
      Provider<OrganizationsRepository> organizationsRepositoryProvider) {
    return new GetEventDetailUseCase_Factory(eventsApiRepositoryProvider, categoryRepositoryProvider, organizationsRepositoryProvider);
  }

  public static GetEventDetailUseCase newInstance(EventsRepository eventsApiRepository,
      CategoryRepository categoryRepository, OrganizationsRepository organizationsRepository) {
    return new GetEventDetailUseCase(eventsApiRepository, categoryRepository, organizationsRepository);
  }
}

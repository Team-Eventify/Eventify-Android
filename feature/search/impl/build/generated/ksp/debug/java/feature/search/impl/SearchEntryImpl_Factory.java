package feature.search.impl;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
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
public final class SearchEntryImpl_Factory implements Factory<SearchEntryImpl> {
  @Override
  public SearchEntryImpl get() {
    return newInstance();
  }

  public static SearchEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SearchEntryImpl newInstance() {
    return new SearchEntryImpl();
  }

  private static final class InstanceHolder {
    static final SearchEntryImpl_Factory INSTANCE = new SearchEntryImpl_Factory();
  }
}

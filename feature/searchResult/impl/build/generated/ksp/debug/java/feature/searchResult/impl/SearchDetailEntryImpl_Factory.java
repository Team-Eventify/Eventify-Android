package feature.searchResult.impl;

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
public final class SearchDetailEntryImpl_Factory implements Factory<SearchDetailEntryImpl> {
  @Override
  public SearchDetailEntryImpl get() {
    return newInstance();
  }

  public static SearchDetailEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SearchDetailEntryImpl newInstance() {
    return new SearchDetailEntryImpl();
  }

  private static final class InstanceHolder {
    static final SearchDetailEntryImpl_Factory INSTANCE = new SearchDetailEntryImpl_Factory();
  }
}

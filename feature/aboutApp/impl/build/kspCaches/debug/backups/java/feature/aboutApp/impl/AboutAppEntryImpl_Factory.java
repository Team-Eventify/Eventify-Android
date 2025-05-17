package feature.aboutApp.impl;

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
public final class AboutAppEntryImpl_Factory implements Factory<AboutAppEntryImpl> {
  @Override
  public AboutAppEntryImpl get() {
    return newInstance();
  }

  public static AboutAppEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AboutAppEntryImpl newInstance() {
    return new AboutAppEntryImpl();
  }

  private static final class InstanceHolder {
    static final AboutAppEntryImpl_Factory INSTANCE = new AboutAppEntryImpl_Factory();
  }
}

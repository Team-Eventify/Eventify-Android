package feature.setup.impl;

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
public final class SetUpEntryImpl_Factory implements Factory<SetUpEntryImpl> {
  @Override
  public SetUpEntryImpl get() {
    return newInstance();
  }

  public static SetUpEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static SetUpEntryImpl newInstance() {
    return new SetUpEntryImpl();
  }

  private static final class InstanceHolder {
    static final SetUpEntryImpl_Factory INSTANCE = new SetUpEntryImpl_Factory();
  }
}

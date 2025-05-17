package feature.myEvents.impl;

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
public final class MyEventsEntryImpl_Factory implements Factory<MyEventsEntryImpl> {
  @Override
  public MyEventsEntryImpl get() {
    return newInstance();
  }

  public static MyEventsEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static MyEventsEntryImpl newInstance() {
    return new MyEventsEntryImpl();
  }

  private static final class InstanceHolder {
    static final MyEventsEntryImpl_Factory INSTANCE = new MyEventsEntryImpl_Factory();
  }
}

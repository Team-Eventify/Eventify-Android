package feature.eventDetail.impl;

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
public final class EventDetailEntryImpl_Factory implements Factory<EventDetailEntryImpl> {
  @Override
  public EventDetailEntryImpl get() {
    return newInstance();
  }

  public static EventDetailEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EventDetailEntryImpl newInstance() {
    return new EventDetailEntryImpl();
  }

  private static final class InstanceHolder {
    static final EventDetailEntryImpl_Factory INSTANCE = new EventDetailEntryImpl_Factory();
  }
}

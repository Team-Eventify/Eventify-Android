package feature.eventFeed.impl;

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
public final class EventsFeedEntryImpl_Factory implements Factory<EventsFeedEntryImpl> {
  @Override
  public EventsFeedEntryImpl get() {
    return newInstance();
  }

  public static EventsFeedEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static EventsFeedEntryImpl newInstance() {
    return new EventsFeedEntryImpl();
  }

  private static final class InstanceHolder {
    static final EventsFeedEntryImpl_Factory INSTANCE = new EventsFeedEntryImpl_Factory();
  }
}

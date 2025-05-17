package feature.profile.impl;

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
public final class ProfileEntryImpl_Factory implements Factory<ProfileEntryImpl> {
  @Override
  public ProfileEntryImpl get() {
    return newInstance();
  }

  public static ProfileEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProfileEntryImpl newInstance() {
    return new ProfileEntryImpl();
  }

  private static final class InstanceHolder {
    static final ProfileEntryImpl_Factory INSTANCE = new ProfileEntryImpl_Factory();
  }
}

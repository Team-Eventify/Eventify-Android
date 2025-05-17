package feature.profileEdit.impl;

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
public final class ProfileEditEntryImpl_Factory implements Factory<ProfileEditEntryImpl> {
  @Override
  public ProfileEditEntryImpl get() {
    return newInstance();
  }

  public static ProfileEditEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ProfileEditEntryImpl newInstance() {
    return new ProfileEditEntryImpl();
  }

  private static final class InstanceHolder {
    static final ProfileEditEntryImpl_Factory INSTANCE = new ProfileEditEntryImpl_Factory();
  }
}

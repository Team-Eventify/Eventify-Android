package feature.login.impl;

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
public final class LoginEntryImpl_Factory implements Factory<LoginEntryImpl> {
  @Override
  public LoginEntryImpl get() {
    return newInstance();
  }

  public static LoginEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LoginEntryImpl newInstance() {
    return new LoginEntryImpl();
  }

  private static final class InstanceHolder {
    static final LoginEntryImpl_Factory INSTANCE = new LoginEntryImpl_Factory();
  }
}

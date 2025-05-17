package feature.register.impl;

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
public final class RegisterEntryImpl_Factory implements Factory<RegisterEntryImpl> {
  @Override
  public RegisterEntryImpl get() {
    return newInstance();
  }

  public static RegisterEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static RegisterEntryImpl newInstance() {
    return new RegisterEntryImpl();
  }

  private static final class InstanceHolder {
    static final RegisterEntryImpl_Factory INSTANCE = new RegisterEntryImpl_Factory();
  }
}

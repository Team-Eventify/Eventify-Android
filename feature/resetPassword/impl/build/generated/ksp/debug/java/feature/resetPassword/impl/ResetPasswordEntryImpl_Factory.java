package feature.resetPassword.impl;

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
public final class ResetPasswordEntryImpl_Factory implements Factory<ResetPasswordEntryImpl> {
  @Override
  public ResetPasswordEntryImpl get() {
    return newInstance();
  }

  public static ResetPasswordEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ResetPasswordEntryImpl newInstance() {
    return new ResetPasswordEntryImpl();
  }

  private static final class InstanceHolder {
    static final ResetPasswordEntryImpl_Factory INSTANCE = new ResetPasswordEntryImpl_Factory();
  }
}

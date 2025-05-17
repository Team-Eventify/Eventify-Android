package core.common.secure;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AuthProvider_Factory implements Factory<AuthProvider> {
  @Override
  public AuthProvider get() {
    return newInstance();
  }

  public static AuthProvider_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthProvider newInstance() {
    return new AuthProvider();
  }

  private static final class InstanceHolder {
    static final AuthProvider_Factory INSTANCE = new AuthProvider_Factory();
  }
}

package data.di;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.remote.api.AuthAPI;
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
public final class AuthModule_ProvideAuthAPIFactory implements Factory<AuthAPI> {
  @Override
  public AuthAPI get() {
    return provideAuthAPI();
  }

  public static AuthModule_ProvideAuthAPIFactory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AuthAPI provideAuthAPI() {
    return Preconditions.checkNotNullFromProvides(AuthModule.INSTANCE.provideAuthAPI());
  }

  private static final class InstanceHolder {
    static final AuthModule_ProvideAuthAPIFactory INSTANCE = new AuthModule_ProvideAuthAPIFactory();
  }
}

package core.common;

import core.common.secure.tokens.TokenProvider;
import core.common.storages.LocaleStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("core.common.storages.EncryptedStorage")
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
public final class CoreCommonModule_ProvideTokenManagerFactory implements Factory<TokenProvider> {
  private final Provider<LocaleStorage> localeStorageProvider;

  public CoreCommonModule_ProvideTokenManagerFactory(
      Provider<LocaleStorage> localeStorageProvider) {
    this.localeStorageProvider = localeStorageProvider;
  }

  @Override
  public TokenProvider get() {
    return provideTokenManager(localeStorageProvider.get());
  }

  public static CoreCommonModule_ProvideTokenManagerFactory create(
      Provider<LocaleStorage> localeStorageProvider) {
    return new CoreCommonModule_ProvideTokenManagerFactory(localeStorageProvider);
  }

  public static TokenProvider provideTokenManager(LocaleStorage localeStorage) {
    return Preconditions.checkNotNullFromProvides(CoreCommonModule.INSTANCE.provideTokenManager(localeStorage));
  }
}

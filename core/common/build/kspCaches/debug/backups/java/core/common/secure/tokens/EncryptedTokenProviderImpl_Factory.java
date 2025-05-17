package core.common.secure.tokens;

import core.common.storages.LocaleStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class EncryptedTokenProviderImpl_Factory implements Factory<EncryptedTokenProviderImpl> {
  private final Provider<LocaleStorage> localeStorageProvider;

  public EncryptedTokenProviderImpl_Factory(Provider<LocaleStorage> localeStorageProvider) {
    this.localeStorageProvider = localeStorageProvider;
  }

  @Override
  public EncryptedTokenProviderImpl get() {
    return newInstance(localeStorageProvider.get());
  }

  public static EncryptedTokenProviderImpl_Factory create(
      Provider<LocaleStorage> localeStorageProvider) {
    return new EncryptedTokenProviderImpl_Factory(localeStorageProvider);
  }

  public static EncryptedTokenProviderImpl newInstance(LocaleStorage localeStorage) {
    return new EncryptedTokenProviderImpl(localeStorage);
  }
}

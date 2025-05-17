package core.common;

import android.content.Context;
import core.common.storages.LocaleStorage;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata({
    "core.common.storages.EncryptedStorage",
    "dagger.hilt.android.qualifiers.ApplicationContext"
})
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
public final class CoreCommonModule_ProvideEncryptedStorageFactory implements Factory<LocaleStorage> {
  private final Provider<Context> contextProvider;

  public CoreCommonModule_ProvideEncryptedStorageFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocaleStorage get() {
    return provideEncryptedStorage(contextProvider.get());
  }

  public static CoreCommonModule_ProvideEncryptedStorageFactory create(
      Provider<Context> contextProvider) {
    return new CoreCommonModule_ProvideEncryptedStorageFactory(contextProvider);
  }

  public static LocaleStorage provideEncryptedStorage(Context context) {
    return Preconditions.checkNotNullFromProvides(CoreCommonModule.INSTANCE.provideEncryptedStorage(context));
  }
}

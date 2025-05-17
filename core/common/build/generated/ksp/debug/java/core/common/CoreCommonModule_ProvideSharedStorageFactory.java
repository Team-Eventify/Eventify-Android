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
    "core.common.storages.SharedStorage",
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
public final class CoreCommonModule_ProvideSharedStorageFactory implements Factory<LocaleStorage> {
  private final Provider<Context> contextProvider;

  public CoreCommonModule_ProvideSharedStorageFactory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public LocaleStorage get() {
    return provideSharedStorage(contextProvider.get());
  }

  public static CoreCommonModule_ProvideSharedStorageFactory create(
      Provider<Context> contextProvider) {
    return new CoreCommonModule_ProvideSharedStorageFactory(contextProvider);
  }

  public static LocaleStorage provideSharedStorage(Context context) {
    return Preconditions.checkNotNullFromProvides(CoreCommonModule.INSTANCE.provideSharedStorage(context));
  }
}

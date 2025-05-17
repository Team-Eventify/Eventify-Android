package core.common.storages;

import android.content.Context;
import com.google.gson.Gson;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class SharedPreferencesStorageImpl_Factory implements Factory<SharedPreferencesStorageImpl> {
  private final Provider<Context> contextProvider;

  private final Provider<String> storageNameProvider;

  private final Provider<Gson> gsonProvider;

  public SharedPreferencesStorageImpl_Factory(Provider<Context> contextProvider,
      Provider<String> storageNameProvider, Provider<Gson> gsonProvider) {
    this.contextProvider = contextProvider;
    this.storageNameProvider = storageNameProvider;
    this.gsonProvider = gsonProvider;
  }

  @Override
  public SharedPreferencesStorageImpl get() {
    return newInstance(contextProvider.get(), storageNameProvider.get(), gsonProvider.get());
  }

  public static SharedPreferencesStorageImpl_Factory create(Provider<Context> contextProvider,
      Provider<String> storageNameProvider, Provider<Gson> gsonProvider) {
    return new SharedPreferencesStorageImpl_Factory(contextProvider, storageNameProvider, gsonProvider);
  }

  public static SharedPreferencesStorageImpl newInstance(Context context, String storageName,
      Gson gson) {
    return new SharedPreferencesStorageImpl(context, storageName, gson);
  }
}

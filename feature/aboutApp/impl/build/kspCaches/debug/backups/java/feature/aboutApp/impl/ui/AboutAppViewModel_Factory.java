package feature.aboutApp.impl.ui;

import android.content.Context;
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
public final class AboutAppViewModel_Factory implements Factory<AboutAppViewModel> {
  private final Provider<Context> contextProvider;

  public AboutAppViewModel_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public AboutAppViewModel get() {
    return newInstance(contextProvider.get());
  }

  public static AboutAppViewModel_Factory create(Provider<Context> contextProvider) {
    return new AboutAppViewModel_Factory(contextProvider);
  }

  public static AboutAppViewModel newInstance(Context context) {
    return new AboutAppViewModel(context);
  }
}

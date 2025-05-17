package core.featureManager;

import core.common.navigation.FeatureEntry;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import java.util.Map;
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
public final class FeaturesProvider_Factory implements Factory<FeaturesProvider> {
  private final Provider<Map<Class<? extends FeatureEntry>, FeatureEntry>> featuresProvider;

  public FeaturesProvider_Factory(
      Provider<Map<Class<? extends FeatureEntry>, FeatureEntry>> featuresProvider) {
    this.featuresProvider = featuresProvider;
  }

  @Override
  public FeaturesProvider get() {
    return newInstance(featuresProvider.get());
  }

  public static FeaturesProvider_Factory create(
      Provider<Map<Class<? extends FeatureEntry>, FeatureEntry>> featuresProvider) {
    return new FeaturesProvider_Factory(featuresProvider);
  }

  public static FeaturesProvider newInstance(
      Map<Class<? extends FeatureEntry>, FeatureEntry> features) {
    return new FeaturesProvider(features);
  }
}

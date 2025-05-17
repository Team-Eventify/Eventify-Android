package feature.onboarding.impl;

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
public final class OnBoardingEntryImpl_Factory implements Factory<OnBoardingEntryImpl> {
  @Override
  public OnBoardingEntryImpl get() {
    return newInstance();
  }

  public static OnBoardingEntryImpl_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static OnBoardingEntryImpl newInstance() {
    return new OnBoardingEntryImpl();
  }

  private static final class InstanceHolder {
    static final OnBoardingEntryImpl_Factory INSTANCE = new OnBoardingEntryImpl_Factory();
  }
}

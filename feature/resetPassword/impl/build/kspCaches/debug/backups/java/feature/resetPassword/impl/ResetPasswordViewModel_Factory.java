package feature.resetPassword.impl;

import androidx.lifecycle.SavedStateHandle;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
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
public final class ResetPasswordViewModel_Factory implements Factory<ResetPasswordViewModel> {
  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public ResetPasswordViewModel_Factory(Provider<SavedStateHandle> savedStateHandleProvider) {
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public ResetPasswordViewModel get() {
    return newInstance(savedStateHandleProvider.get());
  }

  public static ResetPasswordViewModel_Factory create(
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new ResetPasswordViewModel_Factory(savedStateHandleProvider);
  }

  public static ResetPasswordViewModel newInstance(SavedStateHandle savedStateHandle) {
    return new ResetPasswordViewModel(savedStateHandle);
  }
}

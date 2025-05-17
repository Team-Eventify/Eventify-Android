package feature.login.impl;

import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import domain.auth.LoginUseCase;
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
public final class LogInViewModel_Factory implements Factory<LogInViewModel> {
  private final Provider<LoginUseCase> loginUseCaseProvider;

  private final Provider<AuthProvider> authProvider;

  public LogInViewModel_Factory(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    this.loginUseCaseProvider = loginUseCaseProvider;
    this.authProvider = authProvider;
  }

  @Override
  public LogInViewModel get() {
    LogInViewModel instance = newInstance(loginUseCaseProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static LogInViewModel_Factory create(Provider<LoginUseCase> loginUseCaseProvider,
      Provider<AuthProvider> authProvider) {
    return new LogInViewModel_Factory(loginUseCaseProvider, authProvider);
  }

  public static LogInViewModel newInstance(LoginUseCase loginUseCase) {
    return new LogInViewModel(loginUseCase);
  }
}

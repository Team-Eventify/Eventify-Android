package feature.register.impl;

import android.content.Context;
import core.common.BaseViewModel_MembersInjector;
import core.common.secure.AuthProvider;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import data.repositories.auth.AuthUserRepository;
import domain.auth.OtpRegisterUseCase;
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
public final class RegisterViewModel_Factory implements Factory<RegisterViewModel> {
  private final Provider<OtpRegisterUseCase> otpRegisterUseCaseProvider;

  private final Provider<AuthUserRepository> authRepositoryProvider;

  private final Provider<Context> contextProvider;

  private final Provider<AuthProvider> authProvider;

  public RegisterViewModel_Factory(Provider<OtpRegisterUseCase> otpRegisterUseCaseProvider,
      Provider<AuthUserRepository> authRepositoryProvider, Provider<Context> contextProvider,
      Provider<AuthProvider> authProvider) {
    this.otpRegisterUseCaseProvider = otpRegisterUseCaseProvider;
    this.authRepositoryProvider = authRepositoryProvider;
    this.contextProvider = contextProvider;
    this.authProvider = authProvider;
  }

  @Override
  public RegisterViewModel get() {
    RegisterViewModel instance = newInstance(otpRegisterUseCaseProvider.get(), authRepositoryProvider.get(), contextProvider.get());
    BaseViewModel_MembersInjector.injectAuthProvider(instance, authProvider.get());
    return instance;
  }

  public static RegisterViewModel_Factory create(
      Provider<OtpRegisterUseCase> otpRegisterUseCaseProvider,
      Provider<AuthUserRepository> authRepositoryProvider, Provider<Context> contextProvider,
      Provider<AuthProvider> authProvider) {
    return new RegisterViewModel_Factory(otpRegisterUseCaseProvider, authRepositoryProvider, contextProvider, authProvider);
  }

  public static RegisterViewModel newInstance(OtpRegisterUseCase otpRegisterUseCase,
      AuthUserRepository authRepository, Context context) {
    return new RegisterViewModel(otpRegisterUseCase, authRepository, context);
  }
}

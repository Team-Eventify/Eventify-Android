package domain.auth;

import core.common.secure.tokens.TokenProvider;
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
public final class LogOutUseCase_Factory implements Factory<LogOutUseCase> {
  private final Provider<TokenProvider> tokenProvider;

  public LogOutUseCase_Factory(Provider<TokenProvider> tokenProvider) {
    this.tokenProvider = tokenProvider;
  }

  @Override
  public LogOutUseCase get() {
    return newInstance(tokenProvider.get());
  }

  public static LogOutUseCase_Factory create(Provider<TokenProvider> tokenProvider) {
    return new LogOutUseCase_Factory(tokenProvider);
  }

  public static LogOutUseCase newInstance(TokenProvider tokenProvider) {
    return new LogOutUseCase(tokenProvider);
  }
}

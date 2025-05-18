package core.common.validation



sealed interface ValidationError

interface ValidationUseCase<V, R: ValidationError> {
    operator fun invoke(value: V): ValidationResult<V, R>
}
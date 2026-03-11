package com.eonvault.developerutility.commands

enum class DeveloperUtilityErrorCode {
    COMMAND_NOT_REGISTERED,
    PROJECT_REQUIRED,
    INVALID_CONFIGURATION,
    VALIDATION_FAILED,
    EXECUTION_FAILED,
    UNEXPECTED_FAILURE,
}

sealed interface DeveloperUtilityError {
    val code: DeveloperUtilityErrorCode
    val message: String
    val details: String?
    val recoverable: Boolean
}

data class ConfigurationError(
    override val code: DeveloperUtilityErrorCode = DeveloperUtilityErrorCode.INVALID_CONFIGURATION,
    override val message: String,
    override val details: String? = null,
    override val recoverable: Boolean = true
) : DeveloperUtilityError

data class ValidationError(
    override val message: String,
    override val details: String? = null,
    override val recoverable: Boolean = true
) : DeveloperUtilityError {
    override val code: DeveloperUtilityErrorCode = DeveloperUtilityErrorCode.VALIDATION_FAILED
}

data class ExecutionError(
    override val message: String,
    override val details: String? = null,
    override val recoverable: Boolean = true
) : DeveloperUtilityError {
    override val code: DeveloperUtilityErrorCode = DeveloperUtilityErrorCode.EXECUTION_FAILED
}

data class UnexpectedError(
    override val message: String,
    override val details: String? = null,
    override val recoverable: Boolean = false
) : DeveloperUtilityError {
    override val code: DeveloperUtilityErrorCode = DeveloperUtilityErrorCode.UNEXPECTED_FAILURE
}

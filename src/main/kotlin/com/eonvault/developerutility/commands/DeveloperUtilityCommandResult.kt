package com.eonvault.developerutility.commands

sealed interface DeveloperUtilityCommandResult<out T> {
    data class Success<T>(
        val value: T
    ) : DeveloperUtilityCommandResult<T>

    data class Failure(
        val error: DeveloperUtilityError
    ) : DeveloperUtilityCommandResult<Nothing>
}

package com.eonvault.developerutility.commands

import com.eonvault.developerutility.commands.impl.AnalyzeLogsCommand
import com.eonvault.developerutility.commands.impl.ConvertStructuredDataCommand
import com.eonvault.developerutility.commands.impl.ValidateStructuredDataCommand
import com.intellij.openapi.components.Service

@Service(Service.Level.APP)
class DeveloperUtilityCommandRegistry(
    commands: List<DeveloperUtilityCommand> = defaultCommands()
) {
    private val commandsById = commands.associateBy { it.definition.id }

    fun allDefinitions(): List<DeveloperUtilityCommandDefinition> =
        commandsById.values
            .map { it.definition }
            .sortedBy { it.displayName }

    fun find(commandId: DeveloperUtilityCommandId): DeveloperUtilityCommand? = commandsById[commandId]

    fun execute(
        commandId: DeveloperUtilityCommandId,
        context: DeveloperUtilityCommandContext
    ): DeveloperUtilityCommandResult<DeveloperUtilityCommandOutput> {
        val command = find(commandId)
            ?: return DeveloperUtilityCommandResult.Failure(
                error = ConfigurationError(
                    code = DeveloperUtilityErrorCode.COMMAND_NOT_REGISTERED,
                    message = "The ${commandId.name} command is not registered.",
                    details = "Update DeveloperUtilityCommandRegistry to include the command.",
                    recoverable = false
                )
            )

        if (command.definition.requiresOpenProject && context.project == null) {
            return DeveloperUtilityCommandResult.Failure(
                error = ConfigurationError(
                    code = DeveloperUtilityErrorCode.PROJECT_REQUIRED,
                    message = "${command.definition.displayName} requires an open project.",
                    details = "Open a project in the IDE before running this command."
                )
            )
        }

        return command.execute(context)
    }

    companion object {
        private fun defaultCommands(): List<DeveloperUtilityCommand> = listOf(
            ConvertStructuredDataCommand(),
            ValidateStructuredDataCommand(),
            AnalyzeLogsCommand()
        )
    }
}
